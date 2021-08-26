package ru.marslab.filmoteca.data

import android.content.SharedPreferences
import ru.marslab.filmoteca.BuildConfig
import ru.marslab.filmoteca.domain.model.*
import ru.marslab.filmoteca.domain.repository.Constants
import ru.marslab.filmoteca.domain.repository.Storage
import ru.marslab.filmoteca.domain.util.toLanguage
import ru.marslab.filmoteca.domain.util.toStorageString
import ru.marslab.filmoteca.domain.util.toTimeZone


class StorageImpl(private val sharedPreferences: SharedPreferences) : Storage {

    override fun getGuestSession(): Session? {
        val sessionId = sharedPreferences.getString(Constants.GUEST_SESSION_ID, null)
        val sessionExpires = sharedPreferences.getLong(Constants.GUEST_SESSION_EXPIRES, 0L)
        return if (sessionId.isNullOrBlank()) {
            null
        } else {
            Session(sessionId, sessionExpires)
        }
    }

    override fun saveGuestSession(session: Session) {
        sharedPreferences.edit()
            .putString(Constants.GUEST_SESSION_ID, session.id)
            .putLong(Constants.GUEST_SESSION_EXPIRES, session.expiresAt)
            .apply()
    }

    override fun getToken(): Token? {
        val tokenId = sharedPreferences.getString(Constants.TOKEN_ID, null)
        val tokenExpires = sharedPreferences.getLong(Constants.TOKEN_EXPIRES, 0L)
        return if (tokenId.isNullOrBlank()) {
            null
        } else {
            Token(tokenId, tokenExpires)
        }
    }

    override fun saveToken(token: Token) {
        sharedPreferences.edit()
            .putString(Constants.TOKEN_ID, token.id)
            .putLong(Constants.TOKEN_EXPIRES, token.expiresAt)
            .apply()
    }

    override fun getUser(): User? {
        val name = sharedPreferences.getString(Constants.USER_NAME, null)
        val password = sharedPreferences.getString(Constants.USER_PASSWORD, null)
        return if (name.isNullOrBlank() || password.isNullOrBlank()) {
            null
        } else {
            User(name, password)
        }
    }

    override fun saveUser(user: User) {
        sharedPreferences.edit()
            .putString(Constants.USER_NAME, user.name)
            .putString(Constants.USER_PASSWORD, user.password)
            .apply()
    }

    override fun getApikeyV3(): String = BuildConfig.API_KEY_V3

    override fun getApikeyV4(): String = BuildConfig.API_KEY_V4

    override fun getSettingAdult(): Boolean =
        sharedPreferences.getBoolean(Constants.SETTING_ADULT, false)

    override fun getSettingLanguage(): Language? =
        sharedPreferences.getString(Constants.SETTING_LANGUAGE, null)?.toLanguage()

    override fun getSettingTimeZone(): TimeZone? =
        sharedPreferences.getString(Constants.SETTING_TIMEZONE, null)?.toTimeZone()

    override fun saveSettingAdult(value: Boolean) {
        sharedPreferences.edit()
            .putBoolean(Constants.SETTING_ADULT, value)
            .apply()
    }

    override fun saveSettingLanguage(language: Language) {
        sharedPreferences.edit()
            .putString(Constants.SETTING_LANGUAGE, language.toStorageString())
            .apply()
    }

    override fun saveSettingTimeZone(region: TimeZone) {
        sharedPreferences.edit()
            .putString(Constants.SETTING_TIMEZONE, region.toStorageString())
            .apply()
    }
}