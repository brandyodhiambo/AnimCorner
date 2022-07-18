package com.example.animconer.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.animconer.data.converters.Converter
import com.example.animconer.data.local.dao.AnimeDao
import com.example.animconer.data.local.dao.GenresDao
import com.example.animconer.data.local.entity.AnimeEntity
import com.example.animconer.data.local.entity.GenresEntity

@TypeConverters(Converter::class)
@Database(entities = [GenresEntity::class, AnimeEntity::class], version = 1, exportSchema = false)
abstract class AnimeDatabase : RoomDatabase() {
    abstract val genresDao: GenresDao
    abstract val animeDao: AnimeDao
}