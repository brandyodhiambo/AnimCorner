package com.example.animconer.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "table_genres")
data class GenresEntity(
    val count: Int,
    @PrimaryKey
    val malId: Int,
    val name: String,
    val url: String
)
