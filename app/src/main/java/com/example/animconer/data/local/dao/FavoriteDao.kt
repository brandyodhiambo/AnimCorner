package com.example.animconer.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.animconer.data.local.entity.AnimeEntity
import com.example.animconer.data.local.entity.Favorite

@Dao
interface FavoriteDao {

    @Insert
    suspend fun insertFavorite(favorite: Favorite)

    @Query("SELECT * FROM table_favorite ORDER BY malId DESC")
    fun getAllFavorites(): LiveData<List<Favorite>>

    @Query("SELECT isFavorite FROM table_favorite WHERE malId = :malId")
    fun isFavorite(malId: Int): LiveData<Boolean>

    @Query("SELECT * FROM table_anime WHERE malId = :malId")
    fun getOneFavorite(malId: Int): AnimeEntity

    @Delete
    suspend fun deleteOneFavorite(favorite: Favorite)

    @Query("DELETE FROM table_favorite")
    suspend fun deleteAllFavorites()
}