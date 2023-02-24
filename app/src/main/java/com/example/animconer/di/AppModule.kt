package com.example.animconer.di

import android.content.Context
import androidx.room.Room
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.animconer.data.converters.Converter
import com.example.animconer.data.local.database.AnimeDatabase
import com.example.animconer.data.remote.ApiService
import com.example.animconer.data.repository.AnimeRepository
import com.example.animconer.data.repository.CharactersRepository
import com.example.animconer.data.repository.FavoriteRepository
import com.example.animconer.utils.Constants.BASE_URL
import com.example.animconer.utils.Constants.DATABASE_NAME
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
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
    fun provideFavoriteRepository(database: AnimeDatabase): FavoriteRepository {
        return FavoriteRepository(database)
    }

    @Provides
    @Singleton
    fun provideCharactersRepository(
        apiService: ApiService,
        database: AnimeDatabase,
    ): CharactersRepository {
        return CharactersRepository(apiService, database)
    }

    @Provides
    @Singleton
    fun provideApiService(@ApplicationContext context: Context): ApiService {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                },
            )
            .callTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(
                ChuckerInterceptor.Builder(context)
                    .collector(ChuckerCollector(context))
                    .redactHeaders(emptySet())
                    .alwaysReadResponseBody(false)
                    .build(),
            )
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAnimeDatabase(@ApplicationContext context: Context, gson: Gson): AnimeDatabase {
        return Room.databaseBuilder(
            context,
            AnimeDatabase::class.java,
            DATABASE_NAME,
        )
            .allowMainThreadQueries()
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
