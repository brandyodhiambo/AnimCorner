package com.example.animconer.views.screens.home.states

import com.example.animconer.model.AnimeData

data class HomeState(
    val data: List<AnimeData> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
