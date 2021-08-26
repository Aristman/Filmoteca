package ru.marslab.filmoteca.ui.mapper

import ru.marslab.filmoteca.domain.model.Movie
import ru.marslab.filmoteca.ui.model.MovieDetailUi
import ru.marslab.filmoteca.ui.model.MovieShortUi

fun Movie.toUiShort(): MovieShortUi =
    MovieShortUi(
        id,
        title,
        poster,
        release,
        rating
    )

fun Movie.toUiFull(): MovieDetailUi =
    MovieDetailUi(
        id,
        title,
        originalTitle,
        poster,
        backDrop,
        genres.map { it.toString() }, // TODO ("Доделать преобразование жанров из списка ИД в список названий")
        timing,
        release,
        description
    )


