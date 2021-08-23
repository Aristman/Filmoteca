package ru.marslab.filmoteca.domain.repository

import ru.marslab.filmoteca.domain.model.Movie

interface MovieRepository {
    suspend fun getMovieDetails(id: Int): Movie?
    suspend fun getPopularMovies(): List<Movie>?
    suspend fun getTopRatedMovies(): List<Movie>?
}