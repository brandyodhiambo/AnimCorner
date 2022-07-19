package com.example.animconer.data.repository

import androidx.lifecycle.LiveData
import com.example.animconer.data.local.database.AnimeDatabase
import com.example.animconer.data.local.entity.AnimeEntity
import com.example.animconer.data.local.entity.Favorite

class FavoriteRepository(private val database: AnimeDatabase) {
    suspend fun insertFavorite(favorite: Favorite){
        database.favoriteDao.insertFavorite(favorite)
    }
    fun getAllFavorites(): LiveData<List<Favorite>>{
        return database.favoriteDao.getAllFavorites()
    }
    fun isFavorite(id:Int):LiveData<Boolean>{
       return database.favoriteDao.isFavorite(id)
    }
    fun getOneFavorite(id: Int):AnimeEntity{
        return database.favoriteDao.getOneFavorite(id)
    }
    suspend fun deleteOneFavorite(favorite: Favorite){
        database.favoriteDao.deleteOneFavorite(favorite)
    }
    suspend fun deleteAllFavorite(){
        database.favoriteDao.deleteAllFavorites()
    }

}