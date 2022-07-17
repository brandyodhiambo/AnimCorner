package com.example.animconer.views.screens.home.states

import com.example.animconer.model.anime.AnimeData
import com.example.animconer.model.genres.Data

data class HomeState(
    val data:List<AnimeData> = emptyList(),
    val isLoading:Boolean = false,
    val error:String? = null
)
