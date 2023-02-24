package com.example.animconer.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_favorite")
data class Favorite(
    @PrimaryKey
    val malId: Int,
    val title: String?,
    val image: String?,
    val rating: String?,
    val isFavorite: Boolean,
)
