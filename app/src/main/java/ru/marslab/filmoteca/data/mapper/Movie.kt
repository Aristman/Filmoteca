package ru.marslab.filmoteca.data.mapper

import ru.marslab.filmoteca.data.model.guest.GuestRatedMoviesNW
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
                movie.rating
            )
        }