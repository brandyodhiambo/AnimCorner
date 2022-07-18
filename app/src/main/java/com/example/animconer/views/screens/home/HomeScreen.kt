package com.example.animconer.views.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.animconer.R
import com.example.animconer.model.AnimeData
import com.example.animconer.utils.LoadingAnimation
import com.example.animconer.views.screens.destinations.DetailScreenDestination
import com.example.animconer.views.ui.theme.PrimaryDark
import com.example.animconer.views.ui.theme.SecondaryDark
import com.example.animconer.views.ui.theme.SkyBlue
import com.example.animconer.views.ui.theme.White
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val animeState by viewModel.animeState
    val genresState by viewModel.genresState

    Scaffold(
        backgroundColor = PrimaryDark
    )
    { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(8.dp)
            ) {

                item(span = { GridItemSpan(3) }) {
                    TopSection(
                        genresState.data,viewModel
                    )
                }

                items(animeState.data) { anim ->
                    AnimeItem(
                        animeData = anim,
                        navigator = navigator
                    )
                }
            }

            if (animeState.isLoading) {
                LoadingAnimation(
                    modifier = Modifier.align(Center),
                    circleSize = 16.dp

                )
            }

            if (animeState.error != null) {
                Text(
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Center)
                        .padding(16.dp),
                    text = "${animeState.error}",
                    color = Color.Red
                )
            }
        }
    }
}

@Composable
fun TopSection(
    genres: List<String>,
    viewModel: HomeViewModel
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxWidth(),

        ) {

        Image(
            painter = painterResource(id = R.drawable.logo),
            modifier = Modifier
                .size(100.dp)
                .align(CenterHorizontally),
            contentDescription = null
        )

        Text(
            text = "Top Section",
            fontSize = 24.sp,
            color = White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 8.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        var text by remember { mutableStateOf("") }

        OutlinedTextField(
            value = viewModel.searchTerm.value,
            maxLines = 1,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    tint = SkyBlue,
                    contentDescription = null
                )
            },
            onValueChange = {
                viewModel.setSearch(it)
                viewModel.getOneAnime(it)
                },
            label = {
                Text(text = "Search", color = White, textAlign = TextAlign.Start)
            },
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = SkyBlue,
                unfocusedBorderColor = White,
                textColor = Color.White
            ),
            modifier = Modifier
                .padding(start = 8.dp, end = 6.dp)
                .fillMaxWidth()
                .clickable {

                }
        )
        Genres(genres,viewModel)
        Spacer(modifier = Modifier.height(2.dp))
    }
}

@Composable
fun Genres(
    genres: List<String>,
    viewModel: HomeViewModel
) {
    Column(
        Modifier.fillMaxWidth()
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Genres",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = White,
                modifier = Modifier.padding(start = 8.dp, top = 6.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(genres) { genres ->
                Text(
                    color = Color.White,
                    modifier = Modifier
                        .clip(
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clickable {
                            viewModel.setGenres(genres)
                            viewModel.getAnime(viewModel.selectedGenres.value)
                        }
                        .background(
                            if (genres == viewModel.selectedGenres.value) {
                                SecondaryDark
                            } else {
                                SkyBlue
                            }
                        )
                        .padding(16.dp),
                    text = genres,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Justify
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}


@Composable
fun AnimeItem(
    animeData: AnimeData,
    navigator: DestinationsNavigator,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(start = 4.dp)
            .height(200.dp)
            .width(180.dp)
            .clickable {
                navigator.navigate(DetailScreenDestination(animeData = animeData))
            }
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(data = animeData.images?.jpg?.imageUrl)
                    .apply(block = fun ImageRequest.Builder.() {
                        placeholder(R.drawable.logo)
                        crossfade(true)
                    }).build()
            ),
            modifier = modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            contentDescription = null
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
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
        ) {
            animeData.title?.let {
                Text(
                    text = it,
                    maxLines = 1,
                    fontSize = 16.sp,
                    color = White,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
        }
    }

}

