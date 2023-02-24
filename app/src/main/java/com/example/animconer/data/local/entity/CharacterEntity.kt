package com.example.animconer.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.animconer.model.characters.CharacterData

@Entity(tableName = "table_character")
data class CharacterEntity(
    val characters: List<CharacterData>,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
)
