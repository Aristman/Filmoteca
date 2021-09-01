package ru.marslab.filmoteca.ui.mapper

import ru.marslab.filmoteca.domain.model.Movie
import ru.marslab.filmoteca.ui.model.MovieDetailUi
import ru.marslab.filmoteca.ui.model.MovieShortUi

fun Movie.toUiShort(): MovieShortUi =
    MovieShortUi(
        id = id,
        title = title,
        poster = poster,
        releaseDate = release,
        userRating = userRating
    )

fun Movie.toUiFull(): MovieDetailUi =
    MovieDetailUi(
        id = id,
        title = title,
        originalTitle = originalTitle,
        poster = poster,
        backDrop = backDrop,
        genres = genreString ?: "",
        timing = timing,
        release = release,
        popularity = popularity,
        userRating = userRating,
        voteCount = voteCount,
        description = description
    )


