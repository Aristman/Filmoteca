package ru.marslab.filmoteca.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import ru.marslab.filmoteca.data.RepositoryImpl
import ru.marslab.filmoteca.domain.Repository
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object DataModule {

    @ViewModelScoped
    @Provides
    fun provideRepository() : Repository =
        RepositoryImpl()
}