package com.example.animconer.domain.model

import com.example.animconer.R
import com.example.animconer.presentation.screens.destinations.AccountScreenDestination
import com.example.animconer.presentation.screens.destinations.Destination
import com.example.animconer.presentation.screens.destinations.FavoritesScreenDestination
import com.example.animconer.presentation.screens.destinations.HomeScreenDestination

sealed class BottomNavItem(val title:String,val icon :Int, var destination:Destination) {
    object Home :BottomNavItem(
        title = "Home",
        icon = R.drawable.ic_home,
        destination = HomeScreenDestination
    )
    object Favorites : BottomNavItem(
        title = "Favorite",
        icon = R.drawable.ic_favorite,
        destination = FavoritesScreenDestination
    )
    object Account : BottomNavItem(
        title = "Account",
        icon = R.drawable.ic_person,
        destination = AccountScreenDestination
    )
}