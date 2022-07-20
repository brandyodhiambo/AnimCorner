package com.example.animconer.data.mappers

import com.example.animconer.data.local.entity.CharacterEntity
import com.example.animconer.data.remote.response.CharactersResponse
import com.example.animconer.model.characters.CharacterData
import com.example.animconer.model.characters.Person

fun CharacterEntity.toCharacterResponse():CharactersResponse{
    return CharactersResponse(
       `data` = `data`
    )
}

fun CharactersResponse.toCharacterEntity():CharacterEntity{
    return CharacterEntity(
        `data` = `data`
    )
}