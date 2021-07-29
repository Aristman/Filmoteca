package ru.marslab.filmoteca.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.marslab.filmoteca.data.RepositoryImpl
import ru.marslab.filmoteca.domain.Repository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideRepository() : Repository =
        RepositoryImpl()
}