package com.example.animconer.data.mappers

import com.example.animconer.data.local.entity.CharacterEntity
import com.example.animconer.data.remote.response.CharactersResponse

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