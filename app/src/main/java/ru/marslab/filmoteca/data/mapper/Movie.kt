package ru.marslab.filmoteca.data.mapper

import ru.marslab.filmoteca.data.model.guest.GuestRatedMoviesNW
import ru.marslab.filmoteca.data.model.movies.MovieDetailsNW
import ru.marslab.filmoteca.data.model.movies.MoviesNW
import ru.marslab.filmoteca.domain.model.Movie

fun GuestRatedMoviesNW.toDomain(): List<Movie> =
    this.movies.map { movie ->
        Movie(
            movie.id,
            movie.title,
            movie.originalTitle,
            null,
            movie.genreIds,
            movie.popularity,
            movie.posterPath,
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
    this.Movies.map {
        Movie(
            id = it.id,
            title = it.title,
            originalTitle = it.originalTitle,
            budget = null,
            genres = it.genreIds,
            popularity = it.popularity,
            poster = it.posterPath,
            productionCompanies = listOf(),
            release = it.releaseDate,
            spokenLanguages = listOf(),
            originalLanguage = it.originalLanguage,
            rating = it.voteAverage,
            timing = null,
            description = it.overview
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
        posterPath,
        productionCompanies.map { it.name },
        releaseDate,
        spokenLanguages.map { it.name },
        originalLanguage,
        voteAverage,
        timing = null,
        overview
    )