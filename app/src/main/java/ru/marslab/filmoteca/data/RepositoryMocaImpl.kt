package ru.marslab.filmoteca.data

import ru.marslab.filmoteca.domain.Repository
import ru.marslab.filmoteca.ui.model.MovieDetailUi
import ru.marslab.filmoteca.ui.model.RatedMoviesUi


class RepositoryMocaImpl : Repository {

    private val ratedMoviesList = listOf(
        RatedMoviesUi(1, "Властелин колец: Возвращение короля", null, "2003", "6.7"),
        RatedMoviesUi(2, "Интерстеллар", null, "2014", "8.8"),
        RatedMoviesUi(3, "Иван Васильевич меняет профессию", null, "1973", "9.9")
    )

    private val movieDetailList = listOf(
        MovieDetailUi(
            1,
            "Властелин колец: Возвращение короля",
            "Властелин колец: Возвращение короля",
            null,
            listOf("кино", "111"),
            115,
            "2003",
            "Описание фильма"
        ),
        MovieDetailUi(
            2,
            "Интерстеллар",
            "Интерстеллар",
            null,
            listOf("кино", "111"),
            98,
            "2014",
            "Описание фильма \"Интерстеллар\""
        ),
        MovieDetailUi(
            3,
            "Иван Васильевич меняет профессию",
            "Иван Васильевич меняет профессию",
            null,
            listOf("кино", "вино", "домино"),
            89,
            "1973",
            "Описание фильма \"Иван Васильевич меняет профессию\""
        )
    )

    override fun getRatedMovies(): List<RatedMoviesUi> = ratedMoviesList

    override fun getMovieDetail(id: Int): MovieDetailUi? = movieDetailList.find { it.id == id }
}