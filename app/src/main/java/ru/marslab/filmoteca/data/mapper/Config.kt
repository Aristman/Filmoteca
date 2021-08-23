package ru.marslab.filmoteca.data.mapper

import ru.marslab.filmoteca.data.model.configuration.ConfigCountriesNW
import ru.marslab.filmoteca.data.model.configuration.ConfigLanguagesNW
import ru.marslab.filmoteca.data.model.configuration.ConfigTimeZonesNW
import ru.marslab.filmoteca.domain.model.Country
import ru.marslab.filmoteca.domain.model.Language
import ru.marslab.filmoteca.domain.model.TimeZone

fun ConfigCountriesNW.toDomain(): Country =
    Country(iso31661, englishName)

fun ConfigLanguagesNW.toDomain(): Language =
    Language(iso6391, englishName)

fun ConfigTimeZonesNW.toDomain(): TimeZone =
    TimeZone(iso31661, zones)