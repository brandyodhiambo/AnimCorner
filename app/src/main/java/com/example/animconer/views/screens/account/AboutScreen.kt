package com.example.animconer.views.screens.account

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.animconer.R
import com.example.animconer.views.ui.theme.PrimaryDark
import com.example.animconer.views.ui.theme.SkyBlue
import com.example.animconer.views.ui.theme.White
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun AboutScreen(
    navigator: DestinationsNavigator
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 8.dp, end = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AboutAppBar(navigator)
        Spacer(modifier = Modifier.height(8.dp))
        Image(
            painter = painterResource(id = R.drawable.logo),
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth(),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "AnimeConer is an application developed with an intention of providing entertainment platform for young individuals and animation lovers.The app provides several animations and more details about each including there trailers.",
            fontSize = 20.sp,
            color = White,
            textAlign = TextAlign.Center
        )
        Text(
            text = "v1.0.0",
            fontSize = 18.sp,
            color = SkyBlue,
            textAlign = TextAlign.Center
        )

    }

}

@Composable
fun AboutAppBar(
    navigator: DestinationsNavigator
) {
    TopAppBar(
        title = {
            Text(
                text = "About",
                fontSize = 18.sp,
                color = White,
                fontWeight = FontWeight.SemiBold
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                navigator.navigateUp()
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    tint = White,
                    contentDescription = null
                )

            }
        },
        backgroundColor = PrimaryDark,
        elevation = 4.dp
    )

}