package ru.marslab.filmoteca.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jobs")
data class JobsTable(
    @PrimaryKey
    val department: String,
    val jobs: String
)