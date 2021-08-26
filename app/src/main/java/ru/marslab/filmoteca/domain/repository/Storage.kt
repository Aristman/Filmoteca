package ru.marslab.filmoteca.domain.repository

import ru.marslab.filmoteca.domain.model.*

interface Storage {
    fun getGuestSession(): Session?
    fun saveGuestSession(session: Session)

    fun getToken(): Token?
    fun saveToken(token: Token)

    fun getUser(): User?
    fun saveUser(user: User)

    fun getApikeyV3(): String
    fun getApikeyV4(): String

    fun getSettingAdult(): Boolean
    fun getSettingLanguage(): Language?
    fun getSettingTimeZone(): TimeZone?
    fun saveSettingAdult(value: Boolean)
    fun saveSettingLanguage(language: Language)
    fun saveSettingTimeZone(region: TimeZone)

}