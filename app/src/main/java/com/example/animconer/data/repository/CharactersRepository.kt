package com.example.animconer.data.repository

import com.example.animconer.data.local.database.AnimeDatabase
import com.example.animconer.data.mappers.toCharacterEntity
import com.example.animconer.data.mappers.toCharacterResponse
import com.example.animconer.data.remote.ApiService
import com.example.animconer.data.remote.response.CharactersResponse
import com.example.animconer.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class CharactersRepository(
    private val apiService: ApiService,
    private val database: AnimeDatabase
) {
    suspend fun getCharacter(malId: Int): Flow<Resource<CharactersResponse>> {
        return flow {
            val characterFromDB = database.characterDao.getCharacter(malId)
            if (characterFromDB != null) {
                emit(Resource.Success(characterFromDB.toCharacterResponse()))
            } else {
                try {
                    val characterFromApi = apiService.getCharacters(malId)

                    database.characterDao.insertCharacter(characterFromApi.toCharacterEntity())

                    val newCharacterFromDb = database.characterDao.getCharacter(malId)

                    if (newCharacterFromDb != null) {
                        emit(Resource.Success(newCharacterFromDb.toCharacterResponse()))
                    }

                } catch (e: HttpException) {
                    emit(Resource.Error("Unknown error occurred"))
                } catch (e: IOException) {
                    emit(Resource.Error("Couldn't reach the server check your internet connection"))
                }
            }
        }
    }
}