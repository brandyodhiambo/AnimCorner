package com.example.animconer.views.screens.characters

import com.example.animconer.model.characters.CharacterData
import com.example.animconer.model.characters.Person

data class CharacterState(
    val characters :List<CharacterData> = emptyList(),
    val isLoading:Boolean = false,
    val error:String? = null
)