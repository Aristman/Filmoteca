package ru.marslab.filmoteca.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import ru.marslab.filmoteca.data.room.entity.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE login=:login")
    suspend fun getUserInfo(login: String): List<User>

    @Insert(onConflict = REPLACE)
    suspend fun saveUser(user: User)
}