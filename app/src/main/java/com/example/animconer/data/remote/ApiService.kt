package com.example.animconer.data.remote

import com.example.animconer.model.anime.AnimeResponse
import com.example.animconer.model.genres.GenresResponse
import retrofit2.http.GET

interface ApiService {

    @GET("genres/anime")
    suspend fun getGenres():GenresResponse

    @GET("anime")
    suspend fun getAnime():AnimeResponse
}