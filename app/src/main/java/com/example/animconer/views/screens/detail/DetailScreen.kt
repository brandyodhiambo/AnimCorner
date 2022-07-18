package com.example.animconer.views.screens.detail

import android.net.Uri
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.animconer.R
import com.example.animconer.model.AnimeData
import com.example.animconer.views.screens.destinations.CharacterScreenDestination
import com.example.animconer.views.ui.theme.LightGray
import com.example.animconer.views.ui.theme.PrimaryDark
import com.example.animconer.views.ui.theme.SkyBlue
import com.example.animconer.views.ui.theme.White
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun DetailScreen(
    navigator: DestinationsNavigator,
    animeData: AnimeData
) {

    val context = LocalContext.current
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            ImageBanner(
                animeData,
                onClick = {
                    navigator.navigateUp()
                }
            )

        }
        item {
            AnimationDescription(animeData)

        }
        item {
            Trailer(
                animeData,
                navigator
            )
        }

    }

}

@Composable
fun ImageBanner(
    animeData: AnimeData,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(data = animeData.images?.jpg?.imageUrl).apply(block = fun ImageRequest.Builder.() {
                        placeholder(R.drawable.logo)
                        crossfade(true)
                    }).build()
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
        BackButton(onClick)
        AnimationType(animeData.type)

    }

}


@Composable
fun BackButton(
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Start
    ) {
        Button(
            onClick = {
                onClick()
            },
            shape = CircleShape,
            contentPadding = PaddingValues(),
            elevation = ButtonDefaults.elevation(),
            modifier = Modifier
                .width(40.dp)
                .height(40.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White.copy(alpha = 0.3f),
                contentColor = Color.Gray
            )
        ) {
            IconButton(onClick = {
                onClick()
            }) {
                Icon(
                    modifier = Modifier.size(100.dp),
                    painter = painterResource(id = R.drawable.ic_chevron_left),
                    tint = LightGray,
                    contentDescription = null
                )
            }
        }

    }
}

@Composable
fun AnimationType(
    type: String?
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
            Card(
                shape = RoundedCornerShape(20.dp),
                backgroundColor = SkyBlue,
                elevation = 4.dp,
                contentColor = White,
            ) {
                if (type != null) {
                    Text(
                        text = type,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(6.dp)
                    )
                }
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    tint = SkyBlue,
                    modifier = Modifier.size(40.dp)
                )

            }
        }


    }
}

@Composable
fun AnimationDescription(
   animeData: AnimeData
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        animeData.title?.let {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = it,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = SkyBlue,
                textAlign = TextAlign.Start
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        animeData.producers?.get(0)?.let {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = it.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = White,
                textAlign = TextAlign.Start

            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        var expanded by remember { mutableStateOf(false) }
        val textLayoutResultState = remember { mutableStateOf<TextLayoutResult?>(null) }
        val seeMoreSizeState = remember { mutableStateOf<IntSize?>(null) }
        val seeMoreOffsetState = remember { mutableStateOf<Offset?>(null) }

        // getting raw values for smart cast
        val textLayoutResult = textLayoutResultState.value
        val seeMoreSize = seeMoreSizeState.value
        val seeMoreOffset = seeMoreOffsetState.value
        Box {
            animeData.synopsis?.let {
                Text(
                    text = it,
                    fontSize = 13.sp,
                    color = LightGray,
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    )
                    {
                        expanded = false
                    },
                    maxLines = if (expanded) Int.MAX_VALUE else 5,
                    overflow = TextOverflow.Ellipsis,
                    onTextLayout = { textLayoutResultState.value = it },
                )
            }
            if (!expanded) {
                val density = LocalDensity.current
                Text(
                    color = SkyBlue,
                    text = "Read More",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    onTextLayout = { seeMoreSizeState.value = it.size },
                    modifier = Modifier
                        .then(
                            if (seeMoreOffset != null)
                                Modifier.offset(
                                    x = with(density) { seeMoreOffset.x.toDp() },
                                    y = with(density) { seeMoreOffset.y.toDp() },
                                )
                            else Modifier
                        )
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null
                        ) {
                            expanded = true
                            //cutText = null
                        }
                        .alpha(if (seeMoreOffset != null) 1f else 0f)
                )
            }

        }

    }
}

@Composable
fun Trailer(
    animeData: AnimeData,
    navigator: DestinationsNavigator
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {


            Text(
                text = "Trailer",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = LightGray
            )
            Row(
                modifier = Modifier.clickable {
                    navigator.popBackStack()
                    navigator.navigate(CharacterScreenDestination)
                },
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                Text(
                    text = "Characters",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = LightGray
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_chevron_right),
                    tint = White,
                    contentDescription = null
                )
            }

        }
        Card(
            modifier = Modifier
                .height(300.dp)
                .width(600.dp)
                .padding(start = 8.dp, end = 8.dp)
        ) {

            val context = LocalContext.current
            val uri = animeData.trailer?.url ?: "https://www.youtube.com/watch?v=F6Vf9_mOyXQ"
            // Initializing ExoPLayer
            val mExoPlayer = remember(context) {
                ExoPlayer.Builder(context)
                    .build()
                    .apply {
                    val mediaItem = MediaItem.Builder()
                        .setUri(Uri.parse(uri))
                        .build()
                    setMediaItem(mediaItem)

                    prepare()
                    playWhenReady = true
                }
            }
            // Implementing ExoPlayer
            AndroidView(factory = { context ->
                StyledPlayerView(context).apply {
                    player = mExoPlayer

                }
            })
        }
        Spacer(modifier = Modifier.height(8.dp))

    }
}



