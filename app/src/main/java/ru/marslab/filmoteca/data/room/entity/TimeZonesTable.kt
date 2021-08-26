package ru.marslab.filmoteca.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "time_zones")
data class TimeZonesTable(
    @PrimaryKey
    val iso31661: String,
    val names: String
)
