package com.example.animconer.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AnimeData(
    val airing: Boolean?,
    val genres: List<Genre>,
    val image: String,
    val malId: Int?,
    val members: Int?,
    val rating: String?,
    val season: String?,
    val synopsis: String?,
    val title: String?,
    val youtubeVideoId: String?,
    val url: String?,
    val type: String?,
    val year: Int?,
) : Parcelable
