package com.example.animconer.model.anime


import com.google.gson.annotations.SerializedName

data class Trailer(
    @SerializedName("url")
    val url: String?,
    @SerializedName("youtube_id")
    val youtubeId: String?
)