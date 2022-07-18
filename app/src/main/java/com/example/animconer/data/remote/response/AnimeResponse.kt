package com.example.animconer.data.remote.response


import com.example.animconer.model.AnimeData
import com.example.animconer.model.Pagination
import com.google.gson.annotations.SerializedName

data class AnimeResponse(
    @SerializedName("data")
    val `data`: List<AnimeData>,
    @SerializedName("pagination")
    val pagination: Pagination
)