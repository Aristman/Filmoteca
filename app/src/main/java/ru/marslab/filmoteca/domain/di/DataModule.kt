package ru.marslab.filmoteca.domain.di

import android.content.Context
import androidx.room.Room
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.marslab.filmoteca.data.retrofit.MovieApi
import ru.marslab.filmoteca.data.room.database.MainDatabase
import ru.marslab.filmoteca.domain.repository.Store
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun provideRequestService(retrofit: Retrofit): MovieApi =
        retrofit.create(MovieApi::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(Store.BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().setLenient().create()
                )
            )
            .build()

    @Singleton
    @Provides
    fun provideDatabase(context: Context): MainDatabase =
        Room.databaseBuilder(
            context,
            MainDatabase::class.java,
            "main_database.db"
        ).build()
}