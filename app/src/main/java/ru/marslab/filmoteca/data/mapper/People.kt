package ru.marslab.filmoteca.data.mapper

import ru.marslab.filmoteca.data.model.movies.MovieCreditsNW
import ru.marslab.filmoteca.domain.model.Actor
import ru.marslab.filmoteca.domain.model.Employee
import ru.marslab.filmoteca.domain.repository.Constants

fun MovieCreditsNW.toActorsDomain(): List<Actor> =
    this.cast.map { actor ->
        Actor(
            id = actor.id,
            name = actor.name,
            originalName = actor.originalName,
            photoPath = actor.profilePath?.let { Constants.BASE_POSTER_URL + it },
            popularity = actor.popularity
        )
    }

fun MovieCreditsNW.toEmployeeDomain(): List<Employee> =
    this.crew.map { crew ->
        Employee(
            id = crew.id,
            name = crew.name,
            originalName = crew.originalName,
            photoPath = crew.profilePath?.let { Constants.BASE_POSTER_URL + it },
            department = crew.department,
            job = crew.job
        )
    }