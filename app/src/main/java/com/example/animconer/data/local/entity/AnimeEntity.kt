package com.example.animconer.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.animconer.model.anime.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "table_anime")
data class AnimeEntity(
    val airing: Boolean,
    val genres: List<Genre>,
    val jpg: Jpg?,
    @PrimaryKey
    val malId: Int,
    val members: Int,
    val producers: List<Producer>,
    val rating: String,
    val season: String?,
    val synopsis: String,
    val title: String,
    val trailer: Trailer?,
    val url: String?,
    val type: String,
    val year: Int
)
