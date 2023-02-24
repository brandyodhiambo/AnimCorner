package com.example.animconer.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.animconer.data.local.entity.AnimeEntity

@Dao
interface AnimeDao {
    @Insert
    suspend fun insertAnime(anime: List<AnimeEntity>)

    @Query("SELECT *FROM table_anime")
    fun getAnime(): List<AnimeEntity>

    @Query("SELECT * FROM table_anime WHERE title = :name")
    fun getOneAnime(name:String):LiveData<AnimeEntity?>

    @Query("DELETE FROM table_anime")
    suspend fun deleteAllAnime()
}