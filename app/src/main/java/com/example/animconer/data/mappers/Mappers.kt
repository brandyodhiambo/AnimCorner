package com.example.animconer.data.mappers

import com.example.animconer.data.local.entity.GenresEntity
import com.example.animconer.model.Genre

fun GenresEntity.toGenresData(): Genre {
    return Genre(
        malId = malId,
        name = name,
        type = type,
        url = url
    )
}
fun Genre.toGenresEntity(): GenresEntity {
    return GenresEntity(
        malId = malId,
        name = name,
        type = type,
        url = url
    )
}