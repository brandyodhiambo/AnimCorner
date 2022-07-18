package com.example.animconer.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.animconer.model.Genre
import com.example.animconer.model.Images
import com.example.animconer.model.Producer
import com.example.animconer.model.Trailer
import com.example.animconer.utils.Constants.ANIME_TABLE

@Entity(tableName = ANIME_TABLE)
data class AnimeEntity(
    val airing: Boolean?,
    val genres: List<Genre>?,
    val images: Images?,
    @PrimaryKey
    val malId: Int?,
    val members: Int?,
    val producers: List<Producer>?,
    val rating: String?,
    val season: String?,
    val synopsis: String?,
    val title: String?,
    val trailer: Trailer?,
    val url: String?,
    val type: String?,
    val year: Int?
)
