package com.example.animconer.di

import android.content.Context
import androidx.room.Room
import com.example.animconer.data.converters.Converter
import com.example.animconer.data.local.database.AnimeDatabase
import com.example.animconer.data.remote.ApiService
import com.example.animconer.data.repository.AnimeRepo
import com.example.animconer.utils.Constants.BASE_URL
import com.example.animconer.views.screens.home.HomeViewModel
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
    fun provideHomeViewModel(animeRepo: AnimeRepo):HomeViewModel{
        return HomeViewModel(animeRepo)
    }

    @Provides
    @Singleton
    fun provideAmineRepo(apiService: ApiService,database: AnimeDatabase):AnimeRepo{
        return AnimeRepo(apiService,database)
    }

    @Provides
    @Singleton
    fun provideApiService():ApiService{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAnimeDatabase(@ApplicationContext context:Context,gson: Gson): AnimeDatabase {
        return Room.databaseBuilder(
            context,
            AnimeDatabase::class.java,
            "anime_database"
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