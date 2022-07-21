package com.example.animconer.model.characters


import com.google.gson.annotations.SerializedName

data class CharacterData(
    @SerializedName("language")
    val language: String,
    @SerializedName("person")
    val person: Person
)