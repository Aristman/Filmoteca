package ru.marslab.filmoteca.data.mapper

import ru.marslab.filmoteca.data.model.configuration.*
import ru.marslab.filmoteca.data.room.entity.*
import ru.marslab.filmoteca.domain.model.*

private const val JOIN_SEPARATOR = "="

fun ConfigCountriesNW.toDomain(): Country =
    Country(iso31661, englishName)

fun ConfigLanguagesNW.toDomain(): Language {
    val langName = if (name.isBlank() || name.isEmpty()) {
        englishName
    } else {
        name
    }
    return Language(iso6391, langName)
}

fun ConfigTimeZonesNW.toDomain(): TimeZone =
    TimeZone(iso31661, zones)

fun ConfigApiNW.toDomain(): ConfigApi =
    ConfigApi(changeKeys)

fun ConfigJobsNW.toDomain(): Job =
    Job(department, jobs)

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