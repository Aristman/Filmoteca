package ru.marslab.filmoteca.domain.repository

import ru.marslab.filmoteca.data.model.movies.MovieDetailsNW
import ru.marslab.filmoteca.data.model.movies.MoviesNW

interface MovieRepository {
    suspend fun getMovieDetails(id: Int): MovieDetailsNW?
    suspend fun getPopularMovies(): MoviesNW?
    suspend fun getTopRatedMovies(): MoviesNW?
}