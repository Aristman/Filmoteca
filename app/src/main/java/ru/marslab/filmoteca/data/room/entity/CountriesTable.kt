package ru.marslab.filmoteca.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
data class CountriesTable(
    @PrimaryKey
    val iso31661: String,
    val name: String
)
