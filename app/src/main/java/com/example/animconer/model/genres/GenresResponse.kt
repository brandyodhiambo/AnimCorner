package com.example.animconer.model.genres


import com.google.gson.annotations.SerializedName

data class GenresResponse(
    @SerializedName("data")
    val `data`: List<Data>
)