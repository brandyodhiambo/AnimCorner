package com.example.animconer.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.animconer.model.characters.CharacterData
import com.example.animconer.model.characters.ImagesPerson
import com.example.animconer.model.characters.Person
import com.google.gson.annotations.SerializedName

@Entity(tableName = "table_character")
data class CharacterEntity(
    val `data`: List<CharacterData>,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)
