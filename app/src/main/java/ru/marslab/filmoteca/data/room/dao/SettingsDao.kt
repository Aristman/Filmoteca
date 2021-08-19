package ru.marslab.filmoteca.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import androidx.room.Update
import ru.marslab.filmoteca.data.room.entity.Settings

@Dao
interface SettingsDao {
    @Query("SELECT * FROM settings")
    suspend fun getAllSettings(): List<Settings>

    @Query("SELECT * FROM settings WHERE name=:name")
    suspend fun getSetting(name: String): List<Settings>

    @Insert(onConflict = IGNORE)
    suspend fun insertSetting(setting: Settings)

    @Update
    suspend fun updateSetting(setting: Settings)

}