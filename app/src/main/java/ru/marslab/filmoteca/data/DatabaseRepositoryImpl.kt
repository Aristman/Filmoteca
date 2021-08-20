package ru.marslab.filmoteca.data

import ru.marslab.filmoteca.data.room.database.MainDatabase
import ru.marslab.filmoteca.domain.repository.DatabaseRepository

class DatabaseRepositoryImpl(private val database: MainDatabase) : DatabaseRepository {

}