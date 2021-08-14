package ru.marslab.filmoteca.domain.repository

import ru.marslab.filmoteca.domain.model.Movie

interface MovieRepository {
    suspend fun getMovieDetail(id: Int): Movie?
}