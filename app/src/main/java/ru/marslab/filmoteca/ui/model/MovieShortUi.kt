package ru.marslab.filmoteca.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieShortUi(
    val id: Int,
    val title: String,
    val poster: String?,
    val releaseDate: String,
    val userRating: Double
) : Parcelable
