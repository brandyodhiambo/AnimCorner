package com.example.animconer.data.repository

import androidx.lifecycle.LiveData
import com.example.animconer.data.local.database.AnimeDatabase
import com.example.animconer.data.local.entity.AnimeEntity
import com.example.animconer.data.remote.ApiService
import com.example.animconer.data.mappers.toAnimEntity
import com.example.animconer.data.mappers.toAnimeData
import com.example.animconer.data.mappers.toGenresData
import com.example.animconer.data.mappers.toGenresEntity
import com.example.animconer.model.AnimeData
import com.example.animconer.model.Genre
import com.example.animconer.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

class AnimeRepository(
    private val apiService: ApiService,
    private val database: AnimeDatabase
) {
    suspend fun getGenre(): Flow<Resource<List<Genre>>> {
        return flow {
            val genreFromdb = database.genresDao.getGenres()
            if (!genreFromdb.isNullOrEmpty()) {
                emit(Resource.Success(genreFromdb.map { it.toGenresData() }))
            } else {
                try {
                    val genreFromApi = apiService.getGenres()
                    Timber.d("Data : $genreFromApi")
                    database.genresDao.insertGenres(genreFromApi.data.map { it.toGenresEntity() })

                    val newGenresFromdb = database.genresDao.getGenres()
                    if (newGenresFromdb != null) {
                        emit(Resource.Success(newGenresFromdb.map { it.toGenresData() }))
                    }

                } catch (e: HttpException) {
                    emit(Resource.Error("Unknown error occurred"))
                } catch (e: IOException) {
                    emit(Resource.Error("Couldn't reach the server check your internet connection"))
                }
            }
        }.flowOn(Dispatchers.IO)

    }

    suspend fun getAnime(): Flow<Resource<List<AnimeData>>> {
        return flow {
            val animeFromDB = database.animeDao.getAnime()
            if (!animeFromDB.isNullOrEmpty()) {
                emit(Resource.Success(animeFromDB.map { it.toAnimeData() }))
            } else {
                try {
                    val animeDataFromApi = apiService.getAnime()
                    Timber.d("Response ${animeDataFromApi.data}")
                    database.animeDao.insertAnime(animeDataFromApi.data.map { it.toAnimEntity() })
                    val newAnimeDataFromDB = database.animeDao.getAnime()
                    if (!newAnimeDataFromDB.isNullOrEmpty()) {
                        emit(Resource.Success(newAnimeDataFromDB.map { it.toAnimeData() }))
                    }

                } catch (e: HttpException) {
                    emit(Resource.Error("Unknown error occurred"))
                } catch (e: IOException) {
                    emit(Resource.Error("Couldn't reach the server check your internet connection"))
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getOneAnime(name: String): LiveData<AnimeEntity?> {
        return database.animeDao.getOneAnime(name)
    }

}