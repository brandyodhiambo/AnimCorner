package com.example.animconer.data.mappers

import com.example.animconer.data.local.entity.AnimeEntity
import com.example.animconer.data.remote.response.AnimeResponse
import com.example.animconer.data.remote.response.GenresResponse
import com.example.animconer.model.Genre

fun AnimeResponse.Anime.toAnimEntity(): AnimeEntity {
    return AnimeEntity(
        airing = airing,
        genres = genres.map { it.toGenre() },
        image = images.jpg.imageUrl,
        malId = malId,
        members = members,
        rating = rating,
        season = season,
        synopsis = synopsis,
        title = title,
        youtubeVideoId = trailer.youtubeId,
        url = url,
        type = type,
        year = year,
    )
}

fun GenresResponse.Genre.toGenre(): Genre {
    return Genre(
        malId = malId,
        name = name,
    )
}
