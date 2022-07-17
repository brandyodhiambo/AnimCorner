package com.example.animconer.model.anime


import com.google.gson.annotations.SerializedName

data class AnimeData(
    @SerializedName("airing")
    val airing: Boolean,
    @SerializedName("genres")
    val genres: List<Genre>,
    @SerializedName("jpg")
    val jpg: Jpg?,
    @SerializedName("mal_id")
    val malId: Int,
    @SerializedName("members")
    val members: Int,
    @SerializedName("producers")
    val producers: List<Producer>,
    @SerializedName("rating")
    val rating: String,
    @SerializedName("season")
    val season: String?,
    @SerializedName("synopsis")
    val synopsis: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("trailer")
    val trailer: Trailer?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("type")
    val type: String,
    @SerializedName("year")
    val year: Int
)