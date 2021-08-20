package ru.marslab.filmoteca.domain.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ResourceModule {
    @Singleton
    @Provides
    fun providePreference(app: Application): SharedPreferences {
        return app.getSharedPreferences("settings", Context.MODE_PRIVATE)
    }

    @Provides
    fun provideContext(app: Application): Context = app.applicationContext
}