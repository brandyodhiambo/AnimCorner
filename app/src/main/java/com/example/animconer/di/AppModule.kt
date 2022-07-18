package com.example.animconer.di

import android.content.Context
import androidx.room.Room
import com.example.animconer.data.converters.Converter
import com.example.animconer.data.local.database.AnimeDatabase
import com.example.animconer.data.remote.ApiService
import com.example.animconer.data.repository.AnimeRepository
import com.example.animconer.utils.Constants.BASE_URL
import com.example.animconer.utils.Constants.DATABASE_NAME
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAnimeRepository(apiService: ApiService, database: AnimeDatabase): AnimeRepository {
        return AnimeRepository(apiService, database)
    }

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAnimeDatabase(@ApplicationContext context: Context, gson: Gson): AnimeDatabase {
        return Room.databaseBuilder(
            context,
            AnimeDatabase::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .addTypeConverter(Converter(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }
}