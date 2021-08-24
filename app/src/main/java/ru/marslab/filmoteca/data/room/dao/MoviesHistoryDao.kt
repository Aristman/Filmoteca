package ru.marslab.filmoteca.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import ru.marslab.filmoteca.data.room.entity.*

@Dao
interface MoviesHistoryDao {
    @Query("SELECT * FROM movies_history ORDER BY lookTime")
    suspend fun getAllHistory(): List<MoviesHistoryTable>

    @Query("SELECT * FROM movies_history WHERE lookTime BETWEEN :beginTime AND :endTime ORDER BY lookTime")
    suspend fun getPeriodHistory(beginTime: Long, endTime: Long): List<MoviesHistoryTable>

    @Query("SELECT * FROM movies_history WHERE movieId=:movieId")
    suspend fun getMovieData(movieId: Int): List<MoviesHistoryTable>

    @Insert(onConflict = REPLACE)
    suspend fun insertNewMovieData(data: MoviesHistoryTable)

    @Insert(onConflict = REPLACE)
    suspend fun insertCountries(data: List<CountriesTable>)

    @Insert(onConflict = REPLACE)
    suspend fun insertJobs(data: List<JobsTable>)

    @Insert(onConflict = REPLACE)
    suspend fun insertLanguages(data: List<LanguagesTable>)

    @Insert(onConflict = REPLACE)
    suspend fun insertTimeZones(data: List<TimeZonesTable>)

}