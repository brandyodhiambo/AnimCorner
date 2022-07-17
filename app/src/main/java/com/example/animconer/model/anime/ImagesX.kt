package com.example.animconer.model.anime


import com.google.gson.annotations.SerializedName

data class ImagesX(
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("large_image_url")
    val largeImageUrl: String,
    @SerializedName("maximum_image_url")
    val maximumImageUrl: String,
    @SerializedName("medium_image_url")
    val mediumImageUrl: String,
    @SerializedName("small_image_url")
    val smallImageUrl: String
)