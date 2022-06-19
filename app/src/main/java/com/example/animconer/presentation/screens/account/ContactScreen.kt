package com.example.animconer.presentation.screens.account

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.animconer.R
import com.example.animconer.presentation.ui.theme.PrimaryDark
import com.example.animconer.presentation.ui.theme.SecondaryDark
import com.example.animconer.presentation.ui.theme.SkyBlue
import com.example.animconer.presentation.ui.theme.White
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun ContactScreen(
    navigator: DestinationsNavigator
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val context = LocalContext.current

        ContactAppBar(navigator = navigator)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Let's get in touch",
            color = White,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold
        )
        LazyColumn(contentPadding = PaddingValues(8.dp)) {
            val contactItems = listOf(
                ContactItems(
                    "GitHub",
                    R.drawable.ic_github
                ),
                ContactItems(
                    "LinkedIn",
                    R.drawable.ic_linkedin
                ),
                ContactItems(
                    "Twitter",
                    R.drawable.ic_twitter
                ),
                ContactItems(
                    "Facebook",
                    R.drawable.ic_facebook
                )
            )
            items(contactItems) { items ->
                ContactDetails(items = items, onClick = {
                    when(items.title){
                        "GitHub"->{
                            Toast.makeText(context, "GitHub", Toast.LENGTH_SHORT).show()
                        }
                        "LinkedIn"->{

                        }
                        "Twitter"->{

                        }
                        "Facebook"->{

                        }
                    }
                })
            }
        }

    }

}

@Composable
fun ContactAppBar(navigator: DestinationsNavigator) {
    TopAppBar(
        title = {
            Text(
                text = "Contacts",
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
fun ContactDetails(
    items: ContactItems,
    onClick: () -> Unit = {}
) {
    Box {
        Card(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .padding(16.dp)
                .clickable {
                    onClick()
                },
            elevation = 8.dp,
            shape = RoundedCornerShape(12.dp),
            backgroundColor = SecondaryDark
        ) {
        }
        Card(
            modifier = Modifier
                .height(50.dp)
                .width(50.dp)
                .padding(12.dp)
                .clip(CircleShape)
                .clickable {
                    onClick()
                }
                .align(Alignment.TopCenter),
            elevation = 8.dp,
            backgroundColor = White
        ) {
            Icon(
                painter = painterResource(id = items.icon),
               tint = SkyBlue,
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = items.title,
            fontSize = 20.sp,
            color = White,
            modifier = Modifier
                .align(Alignment.Center)
                .clickable {
                    onClick()
                }

        )

    }

}


data class ContactItems(
    val title: String,
    val icon: Int
)