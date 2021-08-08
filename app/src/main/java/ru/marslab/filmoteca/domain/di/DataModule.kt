package ru.marslab.filmoteca.domain.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ru.marslab.filmoteca.App
import ru.marslab.filmoteca.data.RepositoryMocaImpl
import ru.marslab.filmoteca.domain.Repository

@Module
@InstallIn(ViewModelComponent::class)
object DataModule {

    @ViewModelScoped
    @Provides
    fun provideRepository(): Repository = RepositoryMocaImpl()

    @ViewModelScoped
    @Provides
    fun provideContext(app: Application): Context = app.baseContext
}