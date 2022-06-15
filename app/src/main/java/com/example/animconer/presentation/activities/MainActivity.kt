package com.example.animconer.presentation.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.animconer.domain.model.BottomNavItem
import com.example.animconer.presentation.screens.NavGraphs
import com.example.animconer.presentation.ui.theme.*
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.rememberNavHostEngine
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimConerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = PrimaryDark
                ) {


                    val navController = rememberAnimatedNavController()
                    val navHostEngine = rememberNavHostEngine()

                    val newBackStackEntry by navController.currentBackStackEntryAsState()
                    val route = newBackStackEntry?.destination?.route

                    val bottomItems: List<BottomNavItem> = listOf(
                        BottomNavItem.Home,
                        BottomNavItem.Favorites,
                        BottomNavItem.Account
                    )
                    Scaffold(
                        backgroundColor = PrimaryDark,
                        bottomBar = {
                            BottomNavigation(
                                backgroundColor = SecondaryDark,
                                contentColor = SkyBlue,
                                modifier = Modifier
                                    .padding(12.dp)
                                    .shadow(
                                        shape = RoundedCornerShape(15.dp),
                                        clip = true,
                                        elevation = 16.dp
                                    ),
                            ) {
                                val navBackStackEntry by navController.currentBackStackEntryAsState()
                                val currentDestination = navBackStackEntry?.destination
                                bottomItems.forEach { item ->
                                    BottomNavigationItem(
                                        icon = {
                                            Icon(
                                                painter = painterResource(id = item.icon),
                                                contentDescription = null
                                            )
                                        },
                                        label = {
                                            Text(text = item.title)
                                        },
                                        alwaysShowLabel = false,
                                        selectedContentColor = SkyBlue,
                                        unselectedContentColor = White,
                                        selected = currentDestination?.route?.contains(item.destination.route) == true,

                                        onClick = {
                                            navController.navigate(item.destination.route) {
                                                navController.graph.startDestinationRoute?.let { screenRoute ->
                                                    popUpTo(screenRoute) {
                                                        saveState = true
                                                    }
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    ) {paddingValues ->
                       Box(modifier = Modifier.padding(paddingValues)) {
                           DestinationsNavHost(
                               navGraph = NavGraphs.root,
                               navController = navController,
                               engine = navHostEngine
                           )
                       }
                    }
                }
            }
        }
    }
}


