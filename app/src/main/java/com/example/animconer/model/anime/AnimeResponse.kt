package com.example.animconer.model.anime


import com.google.gson.annotations.SerializedName

data class AnimeResponse(
    @SerializedName("data")
    val `data`: List<AnimeData>,
    @SerializedName("pagination")
    val pagination: Pagination
)