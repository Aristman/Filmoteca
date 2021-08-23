package ru.marslab.filmoteca.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.marslab.filmoteca.data.*
import ru.marslab.filmoteca.data.retrofit.MovieApi
import ru.marslab.filmoteca.data.room.database.MainDatabase
import ru.marslab.filmoteca.data.repository.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideUserRepository(api: MovieApi, store: Store): UserRepository =
        UserRepositoryImpl(api, store)

    @Singleton
    @Provides
    fun provideMovieRepository(api: MovieApi, store: Store): MovieRepository =
        MovieRepositoryImpl(api, store)

    @Singleton
    @Provides
    fun provideTvRepository(api: MovieApi, store: Store): TvRepository =
        TvRepositoryImpl(api, store)

    @Singleton
    @Provides
    fun provideDatabaseRepository(database: MainDatabase): DatabaseRepository =
        DatabaseRepositoryImpl(database)

    @Singleton
    @Provides
    fun provideStore(): Store = StoreImpl()

    @Singleton
    @Provides
    fun provideSettingsRepository(api: MovieApi, store: Store): SettingsRepository =
        SettingsRepositoryImpl(api, store)
}