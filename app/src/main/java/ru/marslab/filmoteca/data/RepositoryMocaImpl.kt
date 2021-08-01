package ru.marslab.filmoteca.data

import ru.marslab.filmoteca.domain.Repository
import ru.marslab.filmoteca.domain.model.Film
import ru.marslab.filmoteca.ui.guest.model.RatedMoviesUi
import kotlin.random.Random


class RepositoryMocaImpl: Repository {

    private var filmList = listOf(
        Film("Побег из Шоушенка", 1994),
        Film("Властелин колец: Возвращение короля", 2003),
        Film("Интерстеллар", 2014),
        Film("Форрест Гамп", 1994),
        Film("Иван Васильевич меняет профессию", 1973)
    )

    private val ratedMoviesList = listOf(
        RatedMoviesUi(1, "Властелин колец: Возвращение короля", null, "2003", "6.7"),
        RatedMoviesUi(2, "Интерстеллар", null, "2014", "8.8"),
        RatedMoviesUi(3, "Иван Васильевич меняет профессию", null,"1973", "9.9")
    )

    override fun getRatedMovies(): List<RatedMoviesUi> {
        return ratedMoviesList
    }
}