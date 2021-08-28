package ru.marslab.filmoteca.domain.util

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import com.google.android.gms.maps.model.LatLng
import ru.marslab.filmoteca.domain.model.Language
import ru.marslab.filmoteca.domain.model.TimeZone

private const val JOIN_SEPARATOR = "~"
private const val JOIN_LIST_SEPARATOR = "="

@SuppressLint("NewApi", "SimpleDateFormat")
fun String.toTime(pattern: String): Long? {
    return try {
        val format = SimpleDateFormat()
        format.applyPattern(pattern)
        format.parse(this).time
    } catch (e: Exception) {
        null
    }
}

fun Language.toStorageString(): String =
    iso6391 + JOIN_SEPARATOR + name

fun TimeZone.toStorageString(): String =
    iso31661 + JOIN_SEPARATOR + names.joinToString(separator = JOIN_LIST_SEPARATOR)

fun String.toLanguage(): Language? {
    return try {
        val splitString = this.split(JOIN_SEPARATOR)
        Language(splitString.first(), splitString.last())
    } catch (e: Exception) {
        null
    }
}

fun String.toTimeZone(): TimeZone? {
    return try {
        val splitString = this.split(JOIN_SEPARATOR)
        val namesList = splitString.last().split(JOIN_LIST_SEPARATOR)
        TimeZone(splitString.first(), namesList)
    } catch (e: Exception) {
        null
    }
}

fun String.toLatLng(): LatLng? {
    return try {
        val splitString = this.split(" ")
        if (splitString.size == 2) {
            LatLng(splitString.first().toDouble(), splitString.last().toDouble())
        } else {
            null
        }
    } catch (e: Exception) {
        null
    }
}