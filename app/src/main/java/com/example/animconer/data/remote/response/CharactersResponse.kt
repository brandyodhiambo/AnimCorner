package com.example.animconer.data.remote.response


import com.example.animconer.model.characters.CharacterData
import com.google.gson.annotations.SerializedName

data class CharactersResponse(
    @SerializedName("data")
    val `data`: List<CharacterData>

)