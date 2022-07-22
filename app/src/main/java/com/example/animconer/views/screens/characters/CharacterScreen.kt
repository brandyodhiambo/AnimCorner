package com.example.animconer.views.screens.characters

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.animconer.R
import com.example.animconer.model.AnimeData
import com.example.animconer.model.characters.CharacterData
import com.example.animconer.model.characters.Person
import com.example.animconer.views.ui.theme.PrimaryDark
import com.example.animconer.views.ui.theme.White
import com.nesyou.staggeredgrid.LazyStaggeredGrid
import com.nesyou.staggeredgrid.StaggeredCells
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import timber.log.Timber

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun CharacterScreen(
    navigator: DestinationsNavigator,
    viewModel: CharacterViewModel = hiltViewModel(),
    animeData: AnimeData
) {
    val characterState by viewModel.characterStata
    animeData.malId?.let { viewModel.getCharacter(it) }


    Scaffold(
        backgroundColor = PrimaryDark,
        topBar = {
            CastAppBar(navigator)
        }
    ) {

        Box(Modifier.fillMaxSize()) {
            LazyStaggeredGrid(cells = StaggeredCells.Adaptive(minSize = 180.dp)) {

                items(characterState.characters) { character ->

                    Timber.d(character.person.name)

                    val random: Double = 100 + Math.random() * (500 - 100)
                    Character(
                        characterData = character,
                        modifier = Modifier
                            .height(random.dp)
                            .width(200.dp)
                    )
                }
            }

            if (characterState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }

}

@Composable
fun CastAppBar(navigator: DestinationsNavigator) {
    TopAppBar(
        title = {
            Text(
                text = "Characters Voices",
                fontSize = 24.sp,
                color = White,
                fontWeight = FontWeight.SemiBold
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                navigator.popBackStack()
                navigator.navigateUp()
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    tint = Color.LightGray,
                    contentDescription = null
                )

            }
        },
        backgroundColor = PrimaryDark
    )

}

@Composable
fun Character(
    characterData: CharacterData,
    modifier: Modifier = Modifier
) {
    Box(
        modifier.clickable { }
    ) {
        Image(
            painter = rememberImagePainter(
                data = characterData.person.images.jpg.imageUrl,
                builder = {
                    placeholder(R.drawable.logo)
                    crossfade(true)
                }
            ),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            contentDescription = "Animation"
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colorStops = arrayOf(
                            Pair(0.3f, Color.Transparent),
                            Pair(1.5f, PrimaryDark)
                        )
                    )
                )
        )
        CharacterName(name = characterData.person.name)

    }


}

@Composable
fun CharacterName(name: String) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = name,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = White
            )
        }

    }
}

