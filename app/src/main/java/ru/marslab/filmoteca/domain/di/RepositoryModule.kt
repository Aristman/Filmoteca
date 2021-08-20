package ru.marslab.filmoteca.domain.di

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import ru.marslab.filmoteca.AppDispatchers
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
    fun provideStore(sharedPreferences: SharedPreferences): Store = StoreImpl(sharedPreferences)
}