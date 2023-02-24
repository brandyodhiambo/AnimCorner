package com.example.animconer.data.remote.response

import com.google.gson.annotations.SerializedName

data class GenresResponse(
    @SerializedName("data")
    val genres: List<Genre>,
) {
    data class Genre(
        @SerializedName("count")
        val count: Int,
        @SerializedName("mal_id")
        val malId: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("url")
        val url: String,
    )
}