package com.example.animconer.presentation.screens.detail

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.style.TextAlign
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

    val details =  Details(
        animationName = "Avengers from the west",
        imageUrl = "https://tvovermind.com/wp-content/uploads/2018/06/47.jpg",
        videoUrl = "https://www.youtube.com/watch?v=sGUVKc07xL8",
        producer = "Wyner MacDonald",
        type = "Movie",
        description = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy."
    )
    Scaffold(
        backgroundColor = PrimaryDark
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                ImageBanner(details.imageUrl,details.type)

            }
            item {
                AnimationDescription(details.animationName,details.producer,details.description)

            }
            item {
                Trailer(details.videoUrl)
            }

        }
    }
}

@Composable
fun ImageBanner(
    imageUrl:String,
    type:String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
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
        BackButton()
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
        AnimationType(type)

    }

}


@Composable
fun BackButton() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Start
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
    type:String
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
                Text(
                    text = type,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(6.dp)
                )
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
    animationName:String,
    producer:String,
    description:String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(start = 8.dp, end = 8.dp)
    ) {
        Text(
            text = animationName,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = SkyBlue,
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = producer,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = White,
            textAlign = TextAlign.Start

        )
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
            Text(
                text = description,
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
    videoLink:String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
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
                .height(200.dp)
                .width(600.dp)
                .padding(start = 8.dp, end = 8.dp)
        ) {

            val mContext = LocalContext.current
            val mVideoUrl = videoLink

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
data class Details(
    val animationName:String,
    val imageUrl:String,
    val videoUrl:String,
    val producer:String,
    val type:String,
    val description:String
)


