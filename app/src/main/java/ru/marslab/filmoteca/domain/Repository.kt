package ru.marslab.filmoteca.domain

import ru.marslab.filmoteca.ui.model.MovieDetailUi
import ru.marslab.filmoteca.ui.model.RatedMoviesUi

interface Repository {
    fun getRatedMovies(): List<RatedMoviesUi>
    fun getMovieDetail(id: Int): MovieDetailUi?

}