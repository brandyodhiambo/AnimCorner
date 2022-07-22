package com.example.animconer.views.screens.favorites

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.animconer.views.ui.theme.PrimaryDark
import com.example.animconer.views.ui.theme.SkyBlue
import com.example.animconer.R
import com.example.animconer.data.local.entity.Favorite
import com.example.animconer.views.screens.destinations.DetailScreenDestination
import com.example.animconer.views.ui.theme.White
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun FavoritesScreen(
    navigator: DestinationsNavigator,
    viewModel: FavoriteViewModel = hiltViewModel()
) {


    val openDialog = remember { mutableStateOf(false) }
    val allFavoriteAnime = viewModel.allFavorites.observeAsState(initial = emptyList())

    Scaffold(
        backgroundColor = PrimaryDark,
        topBar = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Favorites",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = White,
                    modifier = Modifier.padding(start = 8.dp)
                )
                IconButton(onClick = {
                    openDialog.value = true
                }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = White
                    )
                }
            }
        }
    ) {
        Box {
            if (allFavoriteAnime.value.isEmpty()) {
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier
                            .size(250.dp),
                        painter = painterResource(id = R.drawable.ic_empty),
                        contentDescription = null
                    )
                }
            }

            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                items(allFavoriteAnime.value) { animItem ->
                    AnimationItems(
                        animItem,
                        navigator,
                        viewModel
                    )
                }
            }
        }

    }
    if (openDialog.value) {
        AlertDialog(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = "Delete All favorite Animations")
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.deleteAllFavorites()
                        openDialog.value = false
                    },
                    colors = ButtonDefaults.buttonColors(SkyBlue)
                ) {
                    Text(text = "Yes", color = Color.White)
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        openDialog.value = false
                    },
                    colors = ButtonDefaults.buttonColors(SkyBlue)
                ) {
                    Text(text = "No", color = Color.White)
                }
            },
            backgroundColor = Color.White,
            contentColor = Color.Black,
            shape = RoundedCornerShape(10.dp)
        )
    }

}


@Composable
fun AnimationItems(
    favorite: Favorite,
    navigator: DestinationsNavigator,
    viewModel: FavoriteViewModel
) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .width(100.dp)
            .height(300.dp)
            .padding(4.dp)
            .clickable {
                val animeData = viewModel.getOneAnime(favorite.malId)
                navigator.navigate(DetailScreenDestination(animeData))
            }
    ) {
        Box {
            Image(
                painter = rememberImagePainter(
                    data = favorite.images?.jpg?.imageUrl,
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
                                Pair(0.3f, Transparent),
                                Pair(1.5f, PrimaryDark)
                            )
                        )
                    )
            )
            IconButton(onClick = {
                viewModel.deleteOneFavorite(favorite)
            }, modifier =  Modifier.align(Alignment.TopEnd)) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    tint = SkyBlue,
                    modifier = Modifier.size(32.dp),
                    contentDescription = null
                )
            }
            AnimationDetails(
                favorite = favorite,
                viewModel = viewModel
            )

        }

    }

}

@Composable
fun AnimationDetails(
    favorite: Favorite,
    viewModel: FavoriteViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                favorite.title?.let {
                    Text(
                        text = it,
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                favorite.rating?.let {
                    Text(
                        text = it,
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light
                    )
                }
            }

        }

    }


}
