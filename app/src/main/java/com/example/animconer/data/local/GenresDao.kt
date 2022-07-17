package com.example.animconer.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GenresDao {

    @Insert
    suspend fun insertGenres(genres:List<GenresEntity>)

    @Query("SELECT * FROM table_genres")
    fun getGenres():List<GenresEntity>?
}