package ru.marslab.filmoteca.domain.di

import android.app.Application
import android.content.Context
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.marslab.filmoteca.data.retrofit.MovieApi

@Module
@InstallIn(ViewModelComponent::class)
object DataModule {

    @ViewModelScoped
    @Provides
    fun provideContext(app: Application): Context = app.baseContext

    @Provides
    fun provideRequestService(retrofit: Retrofit): MovieApi =
        retrofit.create(MovieApi::class.java)

    @Provides
    fun provideRetrofit(baseUrl: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().setLenient().create()
                )
            )
            .build()

    @Provides
    fun provideBaseUrl(): String =
        "https://api.themoviedb.org/3/"
}