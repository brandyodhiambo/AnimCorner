package com.example.animconer.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.animconer.model.Genre
import com.example.animconer.utils.Constants.ANIME_TABLE

@Entity(tableName = ANIME_TABLE)
data class AnimeEntity(
    val airing: Boolean?,
    val genres: List<Genre>,
    val image: String,
    @PrimaryKey val malId: Int?,
    val members: Int?,
    val rating: String?,
    val season: String?,
    val synopsis: String?,
    val title: String?,
    val youtubeVideoId: String?,
    val url: String?,
    val type: String?,
    val year: Int?
)
