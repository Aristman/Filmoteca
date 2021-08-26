package ru.marslab.filmoteca.domain.util

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat

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