package com.example.animconer.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "table_genres")
data class GenresEntity(
    @PrimaryKey
    val malId: Int,
    val name: String,
)
