package com.example.animconer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [GenresEntity::class], version = 1)
abstract class AnimeDatabase:RoomDatabase() {
    abstract val genresDao:GenresDao
}