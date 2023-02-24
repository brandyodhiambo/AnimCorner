package com.example.animconer.data.repository

import androidx.lifecycle.LiveData
import com.example.animconer.data.local.database.AnimeDatabase
import com.example.animconer.data.local.entity.AnimeEntity
import com.example.animconer.data.mappers.toAnimEntity
import com.example.animconer.data.mappers.toAnimeData
import com.example.animconer.data.mappers.toGenresData
import com.example.animconer.data.mappers.toGenresEntity
import com.example.animconer.data.remote.ApiService
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
    private val database: AnimeDatabase,
) {
    suspend fun getGenre(): Flow<Resource<List<Genre>>> {
        return flow {
            val genresFromDb = database.genresDao.getGenres()

            try {
                val genreFromApi = apiService.getGenres().genres
                Timber.d("CharacterData : $genreFromApi")
                database.genresDao.deleteAllGenres()
                database.genresDao.insertGenres(genreFromApi.map { it.toGenresEntity() })
            } catch (e: Exception) {
                Timber.e("Exception : $e")

                emit(
                    Resource.Failure(
                        "Unknown error occurred $e",
                        data = genresFromDb.map { it.toGenresData() },
                    ),
                )
            } catch (e: HttpException) {
                Timber.e("HttpException : $e")

                emit(
                    Resource.Failure(
                        "Unknown error occurred",
                        data = genresFromDb.map { it.toGenresData() },
                    ),
                )
            } catch (e: IOException) {
                Timber.e("IOException : $e")

                emit(
                    Resource.Failure(
                        "Couldn't reach the server check your internet connection",
                        data = genresFromDb.map { it.toGenresData() },
                    ),
                )
            }
            val newGenresFromDb = database.genresDao.getGenres()
            emit(Resource.Success(data = newGenresFromDb.map { it.toGenresData() }))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAnime(): Flow<Resource<List<AnimeData>>> {
        return flow {
            val animeFromDB = database.animeDao.getAnime()

            try {
                val animeDataFromApi = apiService.getAnime().anime

                Timber.d("Response $animeDataFromApi")

                database.animeDao.deleteAllAnime()
                database.animeDao.insertAnime(animeDataFromApi.map { it.toAnimEntity() })
            } catch (e: Exception) {
                Timber.e("Exception : $e")

                emit(
                    Resource.Failure(
                        "Unknown error occurred $e",
                        data = animeFromDB.map { it.toAnimeData() },
                    ),
                )
            } catch (e: HttpException) {
                Timber.e("HttpException : $e")

                emit(
                    Resource.Failure(
                        "Unknown error occurred",
                        data = animeFromDB.map { it.toAnimeData() },
                    ),
                )
            } catch (e: IOException) {
                Timber.e("IOException : $e")

                emit(
                    Resource.Failure(
                        "Couldn't reach the server check your internet connection",
                        data = animeFromDB.map { it.toAnimeData() },
                    ),
                )
            }

            val newAnimeDataFromDB = database.animeDao.getAnime()
            emit(Resource.Success(newAnimeDataFromDB.map { it.toAnimeData() }))
        }.flowOn(Dispatchers.IO)
    }

    fun getOneAnime(name: String): LiveData<AnimeEntity?> {
        return database.animeDao.getOneAnime(name)
    }
}
