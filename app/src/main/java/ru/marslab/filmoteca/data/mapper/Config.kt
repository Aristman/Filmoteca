package ru.marslab.filmoteca.data.mapper

import ru.marslab.filmoteca.data.model.configuration.*
import ru.marslab.filmoteca.domain.model.*

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