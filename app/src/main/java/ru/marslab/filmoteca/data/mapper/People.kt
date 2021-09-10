package ru.marslab.filmoteca.data.mapper

import ru.marslab.filmoteca.data.model.movies.MovieCreditsNW
import ru.marslab.filmoteca.domain.model.Actor
import ru.marslab.filmoteca.domain.model.Employee

fun MovieCreditsNW.toActorsDomain(): List<Actor> =
    this.cast.map { actor ->
        Actor(
            id = actor.id,
            name = actor.name,
            originalName = actor.originalName,
            profilePath = actor.profilePath,
            popularity = actor.popularity
        )
    }

fun MovieCreditsNW.toEmployeeDomain(): List<Employee> =
    this.crew.map { crew ->
        Employee(
            id = crew.id,
            name = crew.name,
            originalName = crew.originalName,
            department = crew.department,
            job = crew.job
        )
    }