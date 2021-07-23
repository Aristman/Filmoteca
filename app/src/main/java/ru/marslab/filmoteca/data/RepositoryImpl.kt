package ru.marslab.filmoteca.data

import ru.marslab.filmoteca.domain.Repository
import ru.marslab.filmoteca.domain.model.Film
import kotlin.random.Random

object RepositoryImpl: Repository {

    private var filmList = listOf(
        Film("Побег из Шоушенка", 1994),
        Film("Властелин колец: Возвращение короля", 2003),
        Film("Интерстеллар", 2014),
        Film("Форрест Гамп", 1994),
        Film("Иван Васильевич меняет профессию", 1973)
    )

    override fun getFilms() = filmList

    override fun getSize() = filmList.size

    override fun getRandomFilm(): Film {
        val randomIndex = Random.nextInt(getSize())
        return filmList[randomIndex]
    }

}