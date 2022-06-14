package com.example.animconer.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.animconer.R
import com.example.animconer.presentation.ui.theme.PrimaryDark
import com.example.animconer.presentation.ui.theme.SkyBlue
import com.example.animconer.presentation.ui.theme.White
import com.google.accompanist.flowlayout.FlowColumn
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.SizeMode
import com.ramcosta.composedestinations.annotation.Destination

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination(start = true)
@Composable
fun HomeScreen(
) {
    Scaffold(
        backgroundColor = PrimaryDark,
        topBar = {
            AppBar()
        }
    )
    {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Explore()
            }
            item {
                Genres()
            }
            item {
                Trending()
            }
            item {
                AiringNow()
            }

            //Airing Items
            val airing = listOf(
                Airing(
                    name = "Spookiz The Movie",
                    imageUrl = "https://theanimationschool.co.za/assets/uploads/home-mobile-fallback-1.jpg"
                ),
                Airing(
                    name = "Spookiz The Movie",
                    imageUrl = "https://theanimationschool.co.za/assets/uploads/home-mobile-fallback-1.jpg"
                ),
                Airing(
                    name = "Spookiz The Movie",
                    imageUrl = "https://theanimationschool.co.za/assets/uploads/home-mobile-fallback-1.jpg"
                ),
                Airing(
                    name = "Spookiz The Movie",
                    imageUrl = "https://theanimationschool.co.za/assets/uploads/home-mobile-fallback-1.jpg"
                ),
                Airing(
                    name = "Spookiz The Movie",
                    imageUrl = "https://theanimationschool.co.za/assets/uploads/home-mobile-fallback-1.jpg"
                ),
                Airing(
                    name = "Spookiz The Movie",
                    imageUrl = "https://theanimationschool.co.za/assets/uploads/home-mobile-fallback-1.jpg"
                ),
            )
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    airing.forEachIndexed { index, airing ->
                        Card(
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .height(90.dp)
                                .padding(start = 8.dp, end = 8.dp)
                                .fillMaxWidth(),
                            backgroundColor = PrimaryDark
                        ) {
                            Row(
                                Modifier.fillMaxSize(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Card(
                                    shape = RoundedCornerShape(10.dp),
                                    modifier = Modifier.width(90.dp).fillMaxHeight()
                                ) {
                                    Image(
                                        painter = rememberImagePainter(
                                            data = airing.imageUrl,
                                            builder = {
                                                placeholder(R.drawable.logo)
                                                crossfade(true)
                                            }
                                        ),
                                        modifier = Modifier.fillMaxSize(),
                                        contentScale = ContentScale.Crop,
                                        alignment = Alignment.Center,
                                        contentDescription = null
                                    )
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Column(
                                    //Modifier.fillMaxHeight(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    Text(
                                        text = airing.name,
                                        fontSize = 18.sp,
                                        color = White,
                                        fontWeight = FontWeight.SemiBold,
                                        modifier = Modifier.padding(start = 8.dp)
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "Airing",
                                        fontSize = 14.sp,
                                        color = SkyBlue,
                                        modifier = Modifier.padding(start = 8.dp)
                                    )
                                }
                            }
                        }
                    }

                }

            }

        }

    }


}

@Composable
fun AppBar() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            modifier = Modifier.size(100.dp),
            contentDescription = null
        )
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
                .padding(start = 10.dp, end = 6.dp)
                .fillMaxWidth()
        )

    }
}

@Composable
fun Genres() {
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
            Text(
                text = "See All",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = SkyBlue,
                modifier = Modifier
                    .padding(end = 8.dp, top = 6.dp)
                    .clickable {
                        //Todo
                    }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        val genres = listOf(
            "Magic",
            "Romance",
            "Comedy",
            "Magic",
            "Romance",
            "Comedy"
        )
        LazyRow {
            items(genres) { genres ->
                Card(
                    shape = RoundedCornerShape(8.dp),
                    backgroundColor = SkyBlue,
                    contentColor = White,
                    elevation = 8.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)

                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        text = genres,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Justify
                    )
                }
            }
        }
    }
}

@Composable
fun Trending() {
    Column(
        Modifier.fillMaxWidth()
    ) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "Trending Now",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = White,
                modifier = Modifier.padding(start = 8.dp, top = 6.dp)
            )
            Text(
                text = "See All",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = SkyBlue,
                modifier = Modifier
                    .padding(end = 8.dp, top = 6.dp)
                    .clickable {
                        //Todo
                    }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        val anim = listOf(
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
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(anim) { anim ->
                Card(
                    shape = RoundedCornerShape(8.dp),
                    elevation = 8.dp,
                    modifier = Modifier
                        .height(200.dp)
                        .width(150.dp)
                        .padding(start = 8.dp)
                ) {
                    Box(
                        Modifier.fillMaxWidth()
                    ) {
                        Image(
                            painter = rememberImagePainter(
                                data = anim.imageUrl,
                                builder = {
                                    placeholder(R.drawable.logo)
                                    crossfade(true)
                                }
                            ),
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop,
                            contentDescription = null
                        )
                        Column(
                            Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Bottom,
                        ) {
                            Text(
                                text = anim.name,
                                fontSize = 16.sp,
                                color = White,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(
                                horizontalArrangement = Arrangement.Start,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_film),
                                    contentDescription = null
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = anim.type,
                                    color = White,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

    }
}


@Composable
fun AiringNow() {
    Column(
        Modifier.fillMaxWidth()
    ) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "Now Airing",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = White,
                modifier = Modifier.padding(start = 8.dp, top = 6.dp)
            )
            Text(
                text = "See All",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = SkyBlue,
                modifier = Modifier
                    .padding(end = 8.dp, top = 6.dp)
                    .clickable {
                        //Todo
                    }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))


        /* LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp),
         modifier = Modifier.fillMaxSize()){
             items(airing){ airing->

             }
         }*/
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
/*
*  Card(
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .height(120.dp)
                                .fillMaxWidth(),
                            backgroundColor = PrimaryDark
                        ) {
                            Row(
                                Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Card(
                                    shape = RoundedCornerShape(10.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Box(modifier = Modifier.fillMaxSize()) {
                                        Image(
                                            painter = rememberImagePainter(
                                                data = airing.imageUrl,
                                                builder = {
                                                    placeholder(R.drawable.logo)
                                                    crossfade(true)
                                                }
                                            ),
                                            modifier = Modifier.fillMaxSize(),
                                            contentScale = ContentScale.Crop,
                                            contentDescription = null
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Column(
                                    //Modifier.fillMaxHeight(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    Text(
                                        text = airing.name,
                                        fontSize = 18.sp,
                                        color = White,
                                        fontWeight = FontWeight.SemiBold,
                                        modifier = Modifier.padding(start = 8.dp)
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "Airing",
                                        fontSize = 14.sp,
                                        color = SkyBlue,
                                        modifier = Modifier.padding(start = 8.dp)
                                    )
                                }
                            }
                        }*/
