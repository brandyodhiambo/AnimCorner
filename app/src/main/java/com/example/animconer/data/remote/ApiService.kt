package com.example.animconer.data.remote

import com.example.animconer.data.remote.response.AnimeResponse
import com.example.animconer.data.remote.response.CharactersResponse
import com.example.animconer.data.remote.response.GenresResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("genres/anime")
    suspend fun getGenres(): GenresResponse

    @GET("anime")
    suspend fun getAnime(): AnimeResponse

    @GET("characters/{animId}/voices")
    suspend fun getCharacters(
        @Path("animId") animId:Int
    ):CharactersResponse
}