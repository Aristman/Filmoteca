package ru.marslab.filmoteca.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.marslab.filmoteca.data.room.dao.MoviesHistoryDao
import ru.marslab.filmoteca.data.room.dao.SettingsDao
import ru.marslab.filmoteca.data.room.dao.UserDao
import ru.marslab.filmoteca.data.room.entity.MoviesHistory
import ru.marslab.filmoteca.data.room.entity.Settings
import ru.marslab.filmoteca.data.room.entity.User

@Database(
    entities = [Settings::class, User::class, MoviesHistory::class],
    version = 1,
    exportSchema = false
)
abstract class MainDatabase : RoomDatabase() {

    abstract fun settingsDao(): SettingsDao
    abstract fun userDao(): UserDao
    abstract fun moviesHistoryDao(): MoviesHistoryDao
}