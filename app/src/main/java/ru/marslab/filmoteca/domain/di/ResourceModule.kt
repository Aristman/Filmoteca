package ru.marslab.filmoteca.domain.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import ru.marslab.filmoteca.AppDispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ResourceModule {
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
    fun providePreference(app: Application): SharedPreferences {
        return app.getSharedPreferences("settings", Context.MODE_PRIVATE)
    }

    @Provides
    fun provideContext(app: Application): Context = app.applicationContext
}