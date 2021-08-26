package ru.marslab.filmoteca.domain.repository

import ru.marslab.filmoteca.domain.model.Language
import ru.marslab.filmoteca.domain.model.Movie
import ru.marslab.filmoteca.domain.model.TimeZone

interface MovieRepository {
    suspend fun getMovieDetails(id: Int): Movie?
    suspend fun getPopularMovies(
        language: Language? = null,
        page: Int? = null,
        region: TimeZone? = null
    ): List<Movie>?

    suspend fun getTopRatedMovies(): List<Movie>?
}