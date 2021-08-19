package ru.marslab.filmoteca.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import ru.marslab.filmoteca.data.room.entity.MoviesHistory

@Dao
interface MoviesHistoryDao {
    @Query("SELECT * FROM movies_history ORDER BY lookTime")
    suspend fun getAllHistory(): List<MoviesHistory>

    @Query("SELECT * FROM movies_history WHERE lookTime BETWEEN :beginTime AND :endTime ORDER BY lookTime")
    suspend fun getPeriodHistory(beginTime: Double, endTime: Double): List<MoviesHistory>

    @Query("SELECT * FROM movies_history WHERE movieId=:movieId")
    suspend fun getMovieData(movieId: Int): List<MoviesHistory>

    @Insert(onConflict = REPLACE)
    suspend fun insertNewMovieData(data: MoviesHistory)

}