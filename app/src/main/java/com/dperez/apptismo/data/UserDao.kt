package com.dperez.apptismo.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateName(user: User)

    @Query("SELECT name FROM DataName ")
    suspend fun getName(): String?
}
