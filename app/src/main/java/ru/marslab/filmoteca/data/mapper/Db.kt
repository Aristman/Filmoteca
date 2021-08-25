package ru.marslab.filmoteca.data.mapper

import ru.marslab.filmoteca.data.room.entity.CountriesTable
import ru.marslab.filmoteca.data.room.entity.JobsTable
import ru.marslab.filmoteca.data.room.entity.LanguagesTable
import ru.marslab.filmoteca.data.room.entity.TimeZonesTable
import ru.marslab.filmoteca.domain.model.Country
import ru.marslab.filmoteca.domain.model.Job
import ru.marslab.filmoteca.domain.model.Language
import ru.marslab.filmoteca.domain.model.TimeZone


private const val JOIN_SEPARATOR = "="

fun Country.toDb(): CountriesTable =
    CountriesTable(
        iso31661, name
    )

fun Job.toDb(): JobsTable =
    JobsTable(
        department,
        jobs.joinToString(separator = JOIN_SEPARATOR)
    )

fun Language.toDb(): LanguagesTable =
    LanguagesTable(
        iso6391, name
    )

fun TimeZone.toDb(): TimeZonesTable =
    TimeZonesTable(
        iso31661,
        names.joinToString(separator = JOIN_SEPARATOR)
    )

fun LanguagesTable.toDomain(): Language =
    Language(iso6391, name)