package com.example.animconer.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.animconer.model.Images

@Entity(tableName = "table_favorite")
data class Favorite(
    @PrimaryKey
    val malId: Int,
    val title: String?,
    val images: Images?,
    val rating: String?,
    val isFavorite:Boolean
)
