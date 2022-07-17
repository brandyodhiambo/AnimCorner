package com.example.animconer.mappers

import com.example.animconer.data.local.entity.GenresEntity
import com.example.animconer.model.genres.Data

fun GenresEntity.toGenresData(): Data {
    return Data(
        malId = malId,
        name = name,
    )
}
fun Data.toGenresEntity(): GenresEntity {
    return GenresEntity(
        malId = malId,
        name = name,
    )
}