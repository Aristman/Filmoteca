package ru.marslab.filmoteca.data.mapper

import ru.marslab.filmoteca.data.model.guest.GuestRatedMoviesNW
import ru.marslab.filmoteca.data.model.movies.MovieDetailsNW
import ru.marslab.filmoteca.data.model.movies.MoviesNW
import ru.marslab.filmoteca.domain.model.Movie
import ru.marslab.filmoteca.domain.repository.Constants

fun GuestRatedMoviesNW.toDomain(): List<Movie> =
    this.movies.map { movie ->
        Movie(
            id = movie.id,
            title = movie.title,
            originalTitle = movie.originalTitle,
            adult = movie.adult,
            backDrop = movie.backdropPath?.let { Constants.BASE_POSTER_URL + it },
            budget = null,
            genres = movie.genreIds,
            poster = movie.posterPath?.let { Constants.BASE_POSTER_URL + it },
            productionCompanies = listOf(),
            release = movie.releaseDate,
            spokenLanguages = listOf(),
            originalLanguage = movie.originalLanguage,
            popularity = movie.popularity,
            userRating = movie.voteAverage,
            voteCount = movie.voteCount,
            timing = null,
            description = movie.overview
        )
    }

fun MoviesNW.toDomain(): List<Movie> =
    this.Movies.map {movie ->
        Movie(
            id = movie.id,
            title = movie.title,
            originalTitle = movie.originalTitle,
            adult = movie.adult,
            backDrop = movie.backdropPath?.let { Constants.BASE_POSTER_URL + it },
            budget = null,
            genres = movie.genreIds,
            poster = movie.posterPath?.let { Constants.BASE_POSTER_URL + it },
            productionCompanies = listOf(),
            release = movie.releaseDate,
            spokenLanguages = listOf(),
            originalLanguage = movie.originalLanguage,
            popularity = movie.popularity,
            userRating = movie.voteAverage,
            voteCount = movie.voteCount,
            timing = null,
            description = movie.overview
        )
    }

fun MovieDetailsNW.toDomain(): Movie =
    Movie(
        id = id,
        title = title,
        originalTitle = originalTitle,
        adult = adult,
        backDrop = backdropPath?.let { Constants.BASE_POSTER_URL + it },
        budget = budget,
        genres = genres.map { it.id },
        poster = posterPath?.let { Constants.BASE_POSTER_URL + it },
        productionCompanies = productionCompanies.map { it.name },
        release = releaseDate,
        spokenLanguages = spokenLanguages.map { it.name },
        originalLanguage = originalLanguage,
        popularity = popularity,
        userRating = voteAverage,
        timing = runtime,
        description = overview,
        voteCount = voteCount
    )