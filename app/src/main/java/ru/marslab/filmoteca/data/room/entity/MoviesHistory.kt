package ru.marslab.filmoteca.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_history")
data class MoviesHistory(
    @PrimaryKey
    val movieId: Int,
    val lookTime: Double,
    val comment: String
)
