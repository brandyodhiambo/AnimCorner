package com.example.animconer.model.characters


import com.google.gson.annotations.SerializedName

data class Person(
    @SerializedName("images")
    val images: ImagesPerson,
    @SerializedName("mal_id")
    val malId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)