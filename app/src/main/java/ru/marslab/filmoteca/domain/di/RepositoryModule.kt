package ru.marslab.filmoteca.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import ru.marslab.filmoteca.data.GuestRepositoryImpl
import ru.marslab.filmoteca.data.MovieRepositoryImpl
import ru.marslab.filmoteca.data.UserRepositoryImpl
import ru.marslab.filmoteca.data.retrofit.MovieApi
import ru.marslab.filmoteca.domain.repository.GuestRepository
import ru.marslab.filmoteca.domain.repository.MovieRepository
import ru.marslab.filmoteca.domain.repository.UserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideUserRepository(api: MovieApi): UserRepository = UserRepositoryImpl(api)


    @Provides
    @Singleton
    fun provideGuestRepository(api: MovieApi): GuestRepository = GuestRepositoryImpl(api)

    @Singleton
    @Provides
    fun provideMovieRepository(api: MovieApi): MovieRepository = MovieRepositoryImpl(api)
}