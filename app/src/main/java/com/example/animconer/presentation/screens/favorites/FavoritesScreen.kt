package com.example.animconer.presentation.screens.favorites

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.animconer.presentation.screens.favorites.common.RatingIndicator
import com.example.animconer.presentation.ui.theme.PrimaryDark
import com.example.animconer.presentation.ui.theme.SkyBlue
import com.example.animconer.presentation.ui.theme.White
import com.example.animconer.R
import com.ramcosta.composedestinations.annotation.Destination

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun FavoritesScreen() {

    val animItem = listOf(
       Favorite(
            imageUrl = "https://www.whatsappimages.in/wp-content/uploads/2021/12/Free-Sad-Cartoon-Images-Wallpaper-2.jpg",
            title = "The Demon Lord",
            releaseDate = "2022-03-12",
            rating = 8F
        ),
        Favorite(
            imageUrl = "https://cdn.myanimelist.net/images/anime/1185/117548.jpg",
            title = "The Demon Lord",
            releaseDate = "2022-03-12",
            rating = 7F
        ),
        Favorite(
            imageUrl = "https://cdn.myanimelist.net/images/anime/1185/117548.jpg",
            title = "The Demon Lord",
            releaseDate = "2022-03-12",
            rating = 8F
        ),
        Favorite(
            imageUrl = "https://www.whatsappimages.in/wp-content/uploads/2021/12/Free-Sad-Cartoon-Images-Wallpaper-2.jpg",
            title = "The Demon Lord",
            releaseDate = "2022-03-12",
            rating = 9F
        ),
        Favorite(
            imageUrl = "https://cdn.myanimelist.net/images/anime/1185/117548.jpg",
            title = "The Demon Lord",
            releaseDate = "2022-03-12",
            rating = 7F
        ),
        Favorite(
            imageUrl = "https://cdn.myanimelist.net/images/anime/1185/117548.jpg",
            title = "The Demon Lord",
            releaseDate = "2022-03-12",
            rating = 9F
        ),
        Favorite(
            imageUrl = "https://www.whatsappimages.in/wp-content/uploads/2021/12/Free-Sad-Cartoon-Images-Wallpaper-2.jpg",
            title = "The Demon Lord",
            releaseDate = "2022-03-12",
            rating = 8F
        ),
    )

    Scaffold(
        backgroundColor = PrimaryDark,
        topBar = {
            FavoriteAppBar()
        }
    ) {
        Column {
            LazyVerticalGrid(columns = GridCells.Fixed(2)){
               items(animItem){ animItem->
                   AnimationItems(animItem = animItem)
               }
            }
        }

    }

}

@Composable
fun FavoriteAppBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Favorites",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = SkyBlue,
            modifier = Modifier.padding(start = 8.dp)
        )
        IconButton(onClick = {
            //Todo
        }) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = null,
                tint = SkyBlue
            )
        }
    }
}

@Composable
fun AnimationItems(
    animItem:Favorite
) {
   Card(
       elevation = 4.dp,
       //shape = RoundedCornerShape(6.dp),
       modifier = Modifier.width(100.dp)
           .height(300.dp)
           .padding(4.dp)
           //.shadow(5.dp, RoundedCornerShape(10.dp))
           .clickable {
               //Todo
           }
   ) {
       Box {
           Image(
               painter = rememberImagePainter(
                   data = animItem.imageUrl,
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
           AnimationDetails(
               title = animItem.title,
               releaseDate = animItem.releaseDate,
               rating =animItem.rating
           )

       }

   }

}

@Composable
fun AnimationDetails(
    title: String,
    releaseDate: String,
    rating: Float
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
                Text(
                    text = title,
                    color = White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = releaseDate,
                    color = White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light
                )
            }
            RatingIndicator(
                percentage = rating
            )

        }

    }

}

data class Favorite(
    val imageUrl: String,
    val title: String,
    val releaseDate: String,
    val rating: Float
)