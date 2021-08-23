package ru.marslab.filmoteca.domain.repository

import ru.marslab.filmoteca.domain.model.*

interface Store {

    var sessionId: String?
    var requestToken: RequestToken?
    var user: User?

    var countries: List<Country>
    var languages: List<Language>
    var timeZones: List<TimeZone>

    var adult: Boolean

    val apiKeyV3: String
    val apiKeyV4: String
}