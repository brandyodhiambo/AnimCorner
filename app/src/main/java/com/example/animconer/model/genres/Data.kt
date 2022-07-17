package com.example.animconer.model.genres


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("mal_id")
    val malId: Int,
    @SerializedName("name")
    val name: String,
)