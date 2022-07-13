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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.animconer.R
import com.example.animconer.views.ui.theme.PrimaryDark
import com.example.animconer.views.ui.theme.White
import com.nesyou.staggeredgrid.LazyStaggeredGrid
import com.nesyou.staggeredgrid.StaggeredCells
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun CharacterScreen(
    navigator: DestinationsNavigator
) {
    val character = listOf(
        CharacterModel(
            name = "Ariana Grande",
            imageUrl = "https://media.vogue.co.uk/photos/623b3d8c447adeae6306abe7/2:3/w_2560%2Cc_limit/arianagrande_276982017_534310181358563_2002221625594365665_n.jpg"
        ),
        CharacterModel(
            name = "Ariana Grande",
            imageUrl = "https://api.time.com/wp-content/uploads/2020/09/time-100-Selena-Gomez.jpg"
        ),
        CharacterModel(
            name = "Ariana Grande",
            imageUrl = "https://media.vogue.co.uk/photos/623b3d8c447adeae6306abe7/2:3/w_2560%2Cc_limit/arianagrande_276982017_534310181358563_2002221625594365665_n.jpg"
        ),
        CharacterModel(
            name = "Ariana Grande",
            imageUrl = "https://api.time.com/wp-content/uploads/2020/09/time-100-Selena-Gomez.jpg"
        ),
        CharacterModel(
            name = "Ariana Grande",
            imageUrl = "https://swordstoday.ie/wp-content/uploads/2020/09/Black-Ish-star-Yara-Shahidi-plays-Tinker-Bella-in-Disneys-live.jpg"
        ),
        CharacterModel(
            name = "Ariana Grande",
            imageUrl = "https://lastfm.freetls.fastly.net/i/u/770x0/47375d7dce7c2b06ae10511cdc6c2e31.jpg"
        ),
        CharacterModel(
            name = "Ariana Grande",
            imageUrl = "https://api.time.com/wp-content/uploads/2020/09/time-100-Selena-Gomez.jpg"
        ),
        CharacterModel(
            name = "Ariana Grande",
            imageUrl = "https://media.vogue.co.uk/photos/623b3d8c447adeae6306abe7/2:3/w_2560%2Cc_limit/arianagrande_276982017_534310181358563_2002221625594365665_n.jpg"
        ),
        CharacterModel(
            name = "Ariana Grande",
            imageUrl = "https://swordstoday.ie/wp-content/uploads/2020/09/Black-Ish-star-Yara-Shahidi-plays-Tinker-Bella-in-Disneys-live.jpg"
        ),

        )
    Scaffold(
        backgroundColor = PrimaryDark,
        topBar = {
            CastAppBar(navigator)
        }
    ) {

       Column {
           LazyStaggeredGrid(cells = StaggeredCells.Adaptive(minSize = 180.dp)) {

               items(character) { character ->
                   val random: Double = 100 + Math.random() * (500 - 100)
                   Character(
                       imageUrl = character.imageUrl,
                       name = character.name,
                       modifier = Modifier
                           .height(random.dp)
                           .width(200.dp)
                   )
               }


           }
       }
    }

}

@Composable
fun CastAppBar(navigator: DestinationsNavigator) {
   TopAppBar(
       title = {
           Text(
               text = "Cast",
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
    imageUrl: String,
    name: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier.clickable {  }
   ) {
        Image(
            painter = rememberImagePainter(
                data = imageUrl,
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
        CharacterName(name = name)

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

data class CharacterModel(
    val name: String,
    val imageUrl: String
)