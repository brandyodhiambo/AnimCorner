package com.example.animconer.data.repository

import com.example.animconer.data.local.AnimeDatabase
import com.example.animconer.data.remote.ApiService
import com.example.animconer.mappers.toGenresData
import com.example.animconer.mappers.toGenresEntity
import com.example.animconer.model.genres.Data
import com.example.animconer.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

class AnimeRepo(
    private val apiService: ApiService,
    private val database: AnimeDatabase
) {
    suspend fun getGenre(): Flow<Resource<List<Data>>> {
       return flow {
           val genreFromdb = database.genresDao.getGenres()
           if (!genreFromdb.isNullOrEmpty()){
               emit(Resource.Success(genreFromdb.map { it.toGenresData() }))
           } else{
               try {
                   val genreFromApi = apiService.getGenres()
                   Timber.d("Data : $genreFromApi")
                   database.genresDao.insertGenres(genreFromApi.data.map { it.toGenresEntity() })

                   val newGenresFromdb = database.genresDao.getGenres()
                   if (newGenresFromdb != null){
                       emit(Resource.Success(newGenresFromdb.map { it.toGenresData() }))
                   }

               } catch (e:HttpException){
                   emit(Resource.Error("Unknown error occurred"))
               } catch (e:IOException){
                   emit(Resource.Error("Couldn't reach the server check your internet connection"))
               }
           }
       }.flowOn(Dispatchers.IO)

    }

}