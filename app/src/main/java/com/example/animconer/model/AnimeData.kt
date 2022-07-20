package com.example.animconer.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AnimeData(
    @SerializedName("airing")
    val airing: Boolean?,
    @SerializedName("genres")
    val genres: List<Genre>?,
    @SerializedName("images")
    val images: Images?,
    @SerializedName("mal_id")
    val malId: Int?,
    @SerializedName("members")
    val members: Int?,
    @SerializedName("producers")
    val producers: List<Producer>?,
    @SerializedName("rating")
    val rating: String?,
    @SerializedName("season")
    val season: String?,
    @SerializedName("synopsis")
    val synopsis: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("trailer")
    val trailer: Trailer?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("year")
    val year: Int?
) : Parcelable