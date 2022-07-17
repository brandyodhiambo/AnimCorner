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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.animconer.R
import com.example.animconer.model.genres.Data
import com.example.animconer.views.screens.destinations.DetailScreenDestination
import com.example.animconer.views.ui.theme.PrimaryDark
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

    val state by viewModel.genresState


    val anim = listOf(
        Anims(
            name = "The Greatest Demon Lord",
            imageUrl = "https://www.whatsappimages.in/wp-content/uploads/2021/12/Free-Sad-Cartoon-Images-Wallpaper-2.jpg",
            type = "TV Series"
        ),
        Anims(
            name = "The Greatest Demon Lord",
            imageUrl = "https://cdn.myanimelist.net/images/anime/1185/117548.jpg",
            type = "TV Series"
        ),
        Anims(
            name = "The Greatest Demon Lord",
            imageUrl = "https://www.whatsappimages.in/wp-content/uploads/2021/12/Free-Sad-Cartoon-Images-Wallpaper-2.jpg",
            type = "TV Series"
        ),
        Anims(
            name = "The Greatest Demon Lord",
            imageUrl = "https://cdn.myanimelist.net/images/anime/1185/117548.jpg",
            type = "TV Series"
        ),
        Anims(
            name = "The Greatest Demon Lord",
            imageUrl = "https://cdn.myanimelist.net/images/anime/1185/117548.jpg",
            type = "TV Series"
        ),
        Anims(
            name = "The Greatest Demon Lord",
            imageUrl = "https://cdn.myanimelist.net/images/anime/1185/117548.jpg",
            type = "TV Series"
        ),
        Anims(
            name = "The Greatest Demon Lord",
            imageUrl = "https://cdn.myanimelist.net/images/anime/1185/117548.jpg",
            type = "TV Series"
        ),
        Anims(
            name = "The Greatest Demon Lord",
            imageUrl = "https://cdn.myanimelist.net/images/anime/1185/117548.jpg",
            type = "TV Series"
        ),
        Anims(
            name = "The Greatest Demon Lord",
            imageUrl = "https://cdn.myanimelist.net/images/anime/1185/117548.jpg",
            type = "TV Series"
        ),
        Anims(
            name = "The Greatest Demon Lord",
            imageUrl = "https://cdn.myanimelist.net/images/anime/1185/117548.jpg",
            type = "TV Series"
        ),
    )

    Scaffold(
        backgroundColor = PrimaryDark
    )
    { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(8.dp),
                content = {
                    item(span = { GridItemSpan(3)}) {
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            modifier = Modifier
                                .size(100.dp)
                                .align(Alignment.Center),
                            contentDescription = null
                        )
                    }
                    item(span = { GridItemSpan(3)}){
                        Explore()
                    }
                    item(span = { GridItemSpan(3)}) {
                        Genres(state.data)
                    }
                    items(anim) { anim ->
                        AnimItem(
                            imageUrl = anim.imageUrl,
                            name = anim.name,
                            type = anim.type,
                            navigator = navigator
                        )
                    }
                })
        }
    }
}


@Composable
fun Explore() {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Explore",
            fontSize = 24.sp,
            color = White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 8.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        var text by remember { mutableStateOf("") }
        OutlinedTextField(
            value = text,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    tint = SkyBlue,
                    contentDescription = null
                )
            },
            onValueChange = { text },
            label = {
                Text(text = "Search", color = White, textAlign = TextAlign.Start)
            },
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = SkyBlue,
                unfocusedBorderColor = White
            ),
            modifier = Modifier
                .padding(start = 8.dp, end = 6.dp)
                .fillMaxWidth()
        )


    }
}

@Composable
fun Genres(
    genres:List<Data>
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
        LazyRow(modifier = Modifier.fillMaxWidth()){
            items(genres){ genres->
                Card(
                    shape = RoundedCornerShape(4.dp),
                    backgroundColor = SkyBlue,
                    contentColor = White,
                    elevation = 8.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 4.dp)

                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        text = genres.name,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Justify
                    )
                }
            }
        }
    }
}


@Composable
fun AnimItem(
    imageUrl: String,
    name: String,
    type: String?,
    navigator: DestinationsNavigator,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(start = 4.dp)
            .height(200.dp)
            .width(180.dp)
            .clickable {
                navigator.navigate(DetailScreenDestination)
            }
    ) {
        Image(
            painter = rememberImagePainter(
                data = imageUrl,
                builder = {
                    placeholder(R.drawable.logo)
                    crossfade(true)
                }
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
            Text(
                text = name,
                maxLines = 1,
                fontSize = 16.sp,
                color = White,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 8.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
    }

}

data class Anims(
    val name: String,
    val imageUrl: String,
    val type: String,
)

data class Airing(
    val imageUrl: String,
    val name: String
)
