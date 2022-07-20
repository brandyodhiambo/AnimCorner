package com.example.animconer.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.animconer.data.local.entity.CharacterEntity

@Dao
interface CharacterDao {

    @Insert
    suspend fun insertCharacter(character: CharacterEntity)

    @Query("SELECT * FROM table_character where id = :id")
    fun getCharacter(id:Int): CharacterEntity?
}