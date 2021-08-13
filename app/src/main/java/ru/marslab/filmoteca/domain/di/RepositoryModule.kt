package ru.marslab.filmoteca.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ru.marslab.filmoteca.data.GuestRepositoryImpl
import ru.marslab.filmoteca.data.RepositoryNetworkImpl
import ru.marslab.filmoteca.data.retrofit.MovieApi
import ru.marslab.filmoteca.domain.repository.GuestRepository
import ru.marslab.filmoteca.domain.repository.Repository

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @ViewModelScoped
    @Provides
    fun provideRepository(api: MovieApi): Repository = RepositoryNetworkImpl(api)

    @ViewModelScoped
    @Provides
    fun provideGuestRepository(api: MovieApi): GuestRepository = GuestRepositoryImpl(api)
}