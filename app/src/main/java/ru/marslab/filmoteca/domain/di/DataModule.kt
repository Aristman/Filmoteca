package ru.marslab.filmoteca.domain.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.marslab.filmoteca.data.RepositoryNetworkImpl
import ru.marslab.filmoteca.data.retrofit.MovieApi
import ru.marslab.filmoteca.domain.Repository
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideRepository(api: MovieApi): Repository = RepositoryNetworkImpl(api)

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
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun provideBaseUrl(): String =
        "https://developers.themoviedb.org/3/"
}