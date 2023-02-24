package com.example.animconer.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.animconer.utils.Constants.GENRES_TABLE

@Entity(tableName = GENRES_TABLE)
data class GenresEntity(
    @PrimaryKey
    val malId: Int,
    val name: String?,
    val type: String?,
    val url: String?
)
