package com.example.animconer.mappers

import com.example.animconer.data.local.entity.AnimeEntity
import com.example.animconer.model.anime.AnimeData

fun AnimeData.toAnimEntity():AnimeEntity{
    return AnimeEntity(
        airing = airing,
        genres = genres,
        jpg= jpg,
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