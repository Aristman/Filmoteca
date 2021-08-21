package ru.marslab.filmoteca.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_history")
data class MoviesHistoryTable(
    @PrimaryKey
    val movieId: Int,
    val lookTime: Long,
    val comment: String
)
