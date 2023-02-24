package com.example.animconer.data.mappers

import com.example.animconer.data.local.entity.GenresEntity
import com.example.animconer.data.remote.response.AnimeResponse
import com.example.animconer.data.remote.response.GenresResponse
import com.example.animconer.model.Genre
import com.example.animconer.model.Images

fun GenresEntity.toGenresData(): Genre {
    return Genre(
        malId = malId,
        name = name,
    )
}

fun GenresResponse.Genre.toGenresEntity(): GenresEntity {
    return GenresEntity(
        count = count,
        malId = malId,
        name = name,
        url = url,
    )
}