package com.example.animconer.data.mappers

import com.example.animconer.data.local.entity.AnimeEntity
import com.example.animconer.model.AnimeData

fun AnimeEntity.toAnimeData(): AnimeData {
    return AnimeData(
        airing = airing,
        genres = genres,
        images= images,
        malId = malId,
        members = members,
        producers = producers,
        rating = rating,
        season = season,
        synopsis = synopsis,
        title = title,
        trailer = trailer,
        url = url,
        type = type,
        year = year
    )
}
