package com.example.animconer.views.screens.home.states

import com.example.animconer.model.genres.Data

data class GenresState(
    val data:List<Data> = emptyList(),
    val isLoading:Boolean = false,
    val error:String? = null
)
