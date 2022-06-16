package com.example.animconer.presentation.screens.detail

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.rememberImagePainter
import com.example.animconer.R
import com.example.animconer.presentation.ui.theme.LightGray
import com.example.animconer.presentation.ui.theme.PrimaryDark
import com.example.animconer.presentation.ui.theme.SkyBlue
import com.example.animconer.presentation.ui.theme.White
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.ramcosta.composedestinations.annotation.Destination

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun DetailScreen() {
    Scaffold(
        backgroundColor = PrimaryDark
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                ImageBanner()
            }
            item {
                AnimationDescription()
            }
            item {
                Trailer()
            }

        }
    }
}

@Composable
fun ImageBanner(
    //imageUrl:String
    //type:String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.6f)
    ) {
        Image(
            painter = rememberImagePainter(
                data = "animItem.imageUrl",
                builder = {
                    placeholder(R.drawable.logo)
                    crossfade(true)
                }
            ),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            contentDescription = "Animation"
        )
        BackButton()
        AnimationType()
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

    }

}


@Composable
fun BackButton() {
    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = SkyBlue
            )
        }
    }
}

@Composable
fun AnimationType(
    //type:String
) {
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Card(
            shape = RoundedCornerShape(20.dp),
            backgroundColor = SkyBlue,
            elevation = 4.dp,
            contentColor = White,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Tv Show", fontSize = 12.sp)
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = null,
                tint = SkyBlue
            )

        }

    }
}

@Composable
fun AnimationDescription(
    /*animationName:String,
    producer:String,
    description:String*/
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "movie Name",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = SkyBlue
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Producer Name",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = White
        )
        Spacer(modifier = Modifier.height(16.dp))

        var expanded by remember { mutableStateOf(false) }
        val textLayoutResultState = remember { mutableStateOf<TextLayoutResult?>(null) }
        val seeMoreSizeState = remember { mutableStateOf<IntSize?>(null) }
        val seeMoreOffsetState = remember { mutableStateOf<Offset?>(null) }

        // getting raw values for smart cast
        val textLayoutResult = textLayoutResultState.value
        val seeMoreSize = seeMoreSizeState.value
        val seeMoreOffset = seeMoreOffsetState.value
        Box {
            Text(
                text = "Description",
                fontSize = 13.sp,
                color = LightGray,
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                )
                {
                    expanded = false
                },
                maxLines = if (expanded) Int.MAX_VALUE else 3,
                overflow = TextOverflow.Ellipsis,
                onTextLayout = { textLayoutResultState.value = it },
            )
            if (!expanded) {
                val density = LocalDensity.current
                Text(
                    color = SkyBlue,
                    text = "... See more",
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
    //videoLink:String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Trailer",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = LightGray
            )
            Text(
                text = "Characters",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = LightGray
            )
        }
        Card(
            modifier = Modifier
                .height(500.dp)
                .fillMaxWidth()
        ) {

            val mContext = LocalContext.current
            val mVideoUrl = "video Link"

            // Declaring ExoPlayer
            val mExoPlayer = remember(mContext) {
                ExoPlayer.Builder(mContext).build().apply {
                    val dataSourceFactory = DefaultDataSourceFactory(
                        mContext,
                        Util.getUserAgent(mContext, mContext.packageName)
                    )
                    val source =
                        ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(
                            Uri.parse(mVideoUrl)
                        )
                    prepare(source)
                }
            }

            // Implementing ExoPlayer
            AndroidView(factory = { context ->
                PlayerView(context).apply {
                    player = mExoPlayer
                }
            })
        }

    }

}


