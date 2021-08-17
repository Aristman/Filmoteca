package ru.marslab.filmoteca.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.marslab.filmoteca.data.MovieRepositoryImpl
import ru.marslab.filmoteca.data.TvRepositoryImpl
import ru.marslab.filmoteca.data.UserRepositoryImpl
import ru.marslab.filmoteca.data.retrofit.MovieApi
import ru.marslab.filmoteca.domain.repository.MovieRepository
import ru.marslab.filmoteca.domain.repository.TvRepository
import ru.marslab.filmoteca.domain.repository.UserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideUserRepository(api: MovieApi): UserRepository = UserRepositoryImpl(api)

    @Singleton
    @Provides
    fun provideMovieRepository(api: MovieApi): MovieRepository = MovieRepositoryImpl(api)

    @Singleton
    @Provides
    fun provideTvRepository(api: MovieApi): TvRepository = TvRepositoryImpl(api)
}