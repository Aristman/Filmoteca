package ru.marslab.filmoteca.domain.repository

import ru.marslab.filmoteca.domain.model.Session
import ru.marslab.filmoteca.domain.model.Token
import ru.marslab.filmoteca.domain.model.User

interface Storage {
    fun getGuestSession(): Session?
    fun saveGuestSession(session: Session)

    fun getToken(): Token?
    fun saveToken(token: Token)

    fun getUser(): User?
    fun saveUser(user: User)

    fun getApikeyV3(): String
    fun getApikeyV4(): String

    val adult: Boolean
    val language: String
    val timeZone: String
    fun saveSettingAdult(value: Boolean)
    fun saveSettingLanguage(language: String)
    fun saveSettingTimeZone(region: String)

}