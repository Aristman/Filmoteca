package ru.marslab.filmoteca.domain.repository

import ru.marslab.filmoteca.data.model.movies.MoviesNW
import ru.marslab.filmoteca.domain.model.Movie

interface MovieRepository {
    suspend fun getMovieDetail(id: Int): Movie?
    suspend fun getPopularMovies(): MoviesNW?
    suspend fun getTopRatedMovies(): MoviesNW?
}