package ru.marslab.filmoteca.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import ru.marslab.filmoteca.AppDispatchers
import ru.marslab.filmoteca.data.DatabaseRepositoryImpl
import ru.marslab.filmoteca.data.MovieRepositoryImpl
import ru.marslab.filmoteca.data.TvRepositoryImpl
import ru.marslab.filmoteca.data.UserRepositoryImpl
import ru.marslab.filmoteca.data.retrofit.MovieApi
import ru.marslab.filmoteca.data.room.database.MainDatabase
import ru.marslab.filmoteca.domain.repository.DatabaseRepository
import ru.marslab.filmoteca.domain.repository.MovieRepository
import ru.marslab.filmoteca.domain.repository.TvRepository
import ru.marslab.filmoteca.domain.repository.UserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun providerDispatcher(): AppDispatchers = object : AppDispatchers {
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
    }
    @Singleton
    @Provides
    fun provideUserRepository(api: MovieApi): UserRepository = UserRepositoryImpl(api)

    @Singleton
    @Provides
    fun provideMovieRepository(api: MovieApi): MovieRepository = MovieRepositoryImpl(api)

    @Singleton
    @Provides
    fun provideTvRepository(api: MovieApi): TvRepository = TvRepositoryImpl(api)

    @Singleton
    @Provides
    fun provideDatabaseRepository(database: MainDatabase): DatabaseRepository = DatabaseRepositoryImpl(database)
}