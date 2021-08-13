package ru.marslab.filmoteca.data

import ru.marslab.filmoteca.data.retrofit.MovieApi
import ru.marslab.filmoteca.domain.model.Movie
import ru.marslab.filmoteca.domain.repository.MovieRepository

class MovieRepositoryImpl(api: MovieApi): MovieRepository {

    override suspend fun getMovieDetail(id: Int): Movie? {
        return null
    }
}