package com.example.animconer.presentation.screens.account

import android.annotation.SuppressLint
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.animconer.R
import com.example.animconer.presentation.screens.destinations.ContactScreenDestination
import com.example.animconer.presentation.ui.theme.LightGray
import com.example.animconer.presentation.ui.theme.PrimaryDark
import com.example.animconer.presentation.ui.theme.SecondaryDark
import com.example.animconer.presentation.ui.theme.White
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun AccountScreen(
    navigator: DestinationsNavigator
) {

    Column(modifier = Modifier.fillMaxSize()) {

        val context = LocalContext.current

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painterResource(
                id = R.drawable.logo
            ),
            contentDescription = "App logo",
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(8.dp)
        )
        LazyColumn(contentPadding = PaddingValues(8.dp)) {
            val accountItems = listOf(
                AccountItems(
                    "About",
                    R.drawable.ic_about
                ),
                AccountItems(
                    "Rate us",
                    R.drawable.ic_rate
                ),
                AccountItems(
                    "Share",
                    R.drawable.ic_share
                ),
                AccountItems(
                    "Help",
                    R.drawable.ic_help
                ),
                AccountItems(
                    "Contact",
                    R.drawable.ic_comment
                )
            )
            items(accountItems) { item ->
                AccountDetails(
                    item = item,
                    onClick = {
                        when (item.title) {
                            "About" -> {
                                Toast.makeText(context, "About", Toast.LENGTH_SHORT).show()
                            }
                            "Rate us" -> {
                                //Todo add playstore code
                                Toast.makeText(context, "Rate Us", Toast.LENGTH_SHORT).show()
                            }
                            "Share" -> {
                                val sendIntent = Intent()
                                sendIntent.action = Intent.ACTION_SEND
                                sendIntent.putExtra(
                                    Intent.EXTRA_TEXT,
                                    "My Animation App"
                                )
                                sendIntent.type = "text/plain"
                                context.startActivity(sendIntent)
                            }
                            "Help" -> {
                                Toast.makeText(context, "Help", Toast.LENGTH_SHORT).show()
                            }
                            "Contact" -> {
                                navigator.navigate(ContactScreenDestination)
                            }
                        }
                    }


                )

            }

        }
        
    }
}

@Composable
fun AccountDetails(
    item: AccountItems,
    onClick: () -> Unit = {},
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp),
        elevation = 12.dp,
        shape = RoundedCornerShape(12.dp),
        backgroundColor = SecondaryDark
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    modifier = Modifier
                        .height(24.dp)
                        .width(24.dp),
                    painter = painterResource(id = item.icon),
                    contentDescription = null,
                    tint = LightGray
                )

                Spacer(modifier = Modifier.width(16.dp))
                Text(text = item.title, color = White)

            }
            Spacer(modifier = Modifier.width(16.dp))
           IconButton(onClick = {onClick()}) {
               Icon(
                   painter = painterResource(id = R.drawable.ic_chevron_right),
                   tint = White,
                   contentDescription = null
               )

           }

        }



    }
}

data class AccountItems(
    val title: String,
    val icon: Int
)

