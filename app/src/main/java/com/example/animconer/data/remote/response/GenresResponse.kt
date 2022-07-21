package com.example.animconer.data.remote.response


import com.example.animconer.model.Genre
import com.google.gson.annotations.SerializedName

data class GenresResponse(
    @SerializedName("data")
    val `data`: List<Genre>
)