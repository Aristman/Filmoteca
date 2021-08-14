package ru.marslab.filmoteca.ui.mapper

import ru.marslab.filmoteca.domain.model.Movie
import ru.marslab.filmoteca.ui.model.MovieShortUi

fun List<Movie>.toUi(): List<MovieShortUi> = this.map { movie ->
    MovieShortUi(
        movie.id,
        movie.title,
        movie.poster,
        movie.release,
        movie.rating
    )
}