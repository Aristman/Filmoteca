package ru.marslab.filmoteca.ui.mapper

import ru.marslab.filmoteca.domain.model.Actor
import ru.marslab.filmoteca.domain.model.Employee
import ru.marslab.filmoteca.ui.model.PeopleShortUi

fun Actor.toShortUi(): PeopleShortUi =
    PeopleShortUi(
        id = id,
        name = name,
        photo = photoPath
    )

fun Employee.toShortUi(): PeopleShortUi =
    PeopleShortUi(
        id = id,
        name = name,
        photo = photoPath
    )