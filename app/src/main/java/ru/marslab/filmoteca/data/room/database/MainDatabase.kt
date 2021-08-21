package ru.marslab.filmoteca.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.marslab.filmoteca.data.room.dao.MoviesHistoryDao
import ru.marslab.filmoteca.data.room.entity.MoviesHistoryTable

@Database(
    entities = [MoviesHistoryTable::class],
    version = 1,
    exportSchema = false
)
abstract class MainDatabase : RoomDatabase() {

    abstract fun moviesHistoryDao(): MoviesHistoryDao
}