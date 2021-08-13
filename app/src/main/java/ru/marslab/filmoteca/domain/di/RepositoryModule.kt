package ru.marslab.filmoteca.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ru.marslab.filmoteca.data.GuestRepositoryImpl
import ru.marslab.filmoteca.data.MovieRepositoryImpl
import ru.marslab.filmoteca.data.UserRepositoryImpl
import ru.marslab.filmoteca.data.retrofit.MovieApi
import ru.marslab.filmoteca.domain.repository.GuestRepository
import ru.marslab.filmoteca.domain.repository.MovieRepository
import ru.marslab.filmoteca.domain.repository.UserRepository

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @ViewModelScoped
    @Provides
    fun provideUserRepository(api: MovieApi): UserRepository = UserRepositoryImpl(api)

    @ViewModelScoped
    @Provides
    fun provideGuestRepository(api: MovieApi): GuestRepository = GuestRepositoryImpl(api)

    @ViewModelScoped
    @Provides
    fun provideMovieRepository(api: MovieApi): MovieRepository = MovieRepositoryImpl(api)
}