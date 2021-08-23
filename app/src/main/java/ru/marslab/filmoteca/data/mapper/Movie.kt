package ru.marslab.filmoteca.data.mapper

import ru.marslab.filmoteca.data.model.guest.GuestRatedMoviesNW
import ru.marslab.filmoteca.data.model.movies.MovieDetailsNW
import ru.marslab.filmoteca.data.model.movies.MoviesNW
import ru.marslab.filmoteca.domain.model.Movie
import ru.marslab.filmoteca.domain.repository.Constants

fun GuestRatedMoviesNW.toDomain(): List<Movie> =
    this.movies.map { movie ->
        Movie(
            movie.id,
            movie.title,
            movie.originalTitle,
            null,
            movie.genreIds,
            movie.popularity,
            movie.posterPath?.let { Constants.BASE_POSTER_URL + it },
            listOf(),
            movie.releaseDate,
            listOf(),
            movie.originalLanguage,
            movie.rating,
            null,
            movie.overview
        )
    }

fun MoviesNW.toDomain(): List<Movie> =
    this.Movies.map {movie ->
        Movie(
            movie.id,
            movie.title,
            movie.originalTitle,
            null,
            movie.genreIds,
            movie.popularity,
            movie.posterPath?.let { Constants.BASE_POSTER_URL + it },
            listOf(),
            movie.releaseDate,
            listOf(),
            movie.originalLanguage,
            movie.voteAverage,
            null,
            movie.overview
        )
    }

fun MovieDetailsNW.toDomain(): Movie =
    Movie(
        id,
        title,
        originalTitle,
        budget,
        genres.map { it.id },
        popularity,
        posterPath?.let { Constants.BASE_POSTER_URL + it },
        productionCompanies.map { it.name },
        releaseDate,
        spokenLanguages.map { it.name },
        originalLanguage,
        voteAverage,
        timing = null,
        overview
    )