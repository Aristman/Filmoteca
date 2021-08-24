package ru.marslab.filmoteca.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "languages")
data class LanguagesTable(
    @PrimaryKey
    val iso6391: String,
    val name: String
)
