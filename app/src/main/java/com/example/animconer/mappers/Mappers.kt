package com.example.animconer.mappers

import com.example.animconer.data.local.GenresEntity
import com.example.animconer.model.genres.Data

fun GenresEntity.toGenresData(): Data {
    return Data(
        count = count,
        malId = malId,
        name = name,
        url = url
    )
}
fun Data.toGenresEntity():GenresEntity{
    return GenresEntity(
        count = count,
        malId = malId,
        name = name,
        url = url
    )
}