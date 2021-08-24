package ru.marslab.filmoteca.domain.di

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.marslab.filmoteca.data.*
import ru.marslab.filmoteca.data.retrofit.MovieApi
import ru.marslab.filmoteca.data.room.database.MainDatabase
import ru.marslab.filmoteca.domain.repository.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideUserRepository(api: MovieApi, storage: Storage): UserRepository =
        UserRepositoryImpl(api, storage)

    @Singleton
    @Provides
    fun provideMovieRepository(api: MovieApi, storage: Storage): MovieRepository =
        MovieRepositoryImpl(api, storage)

    @Singleton
    @Provides
    fun provideTvRepository(api: MovieApi, storage: Storage): TvRepository =
        TvRepositoryImpl(api, storage)

    @Singleton
    @Provides
    fun provideDatabaseRepository(database: MainDatabase): DatabaseRepository =
        DatabaseRepositoryImpl(database)

    @Singleton
    @Provides
    fun provideStorage(sharedPreferences: SharedPreferences): Storage =
        StorageImpl(sharedPreferences)

    @Singleton
    @Provides
    fun provideSettingsRepository(api: MovieApi, storage: Storage): SettingsRepository =
        SettingsRepositoryImpl(api, storage)
}