package com.example.animconer.data.mappers

import com.example.animconer.data.local.entity.AnimeEntity
import com.example.animconer.model.AnimeData

fun AnimeEntity.toAnimeData(): AnimeData {
    return AnimeData(
        airing = airing,
        genres = genres,
        image = image,
        malId = malId,
        members = members,
        rating = rating,
        season = season,
        synopsis = synopsis,
        title = title,
        youtubeVideoId = youtubeVideoId,
        url = url,
        type = type,
        year = year,
    )
}
