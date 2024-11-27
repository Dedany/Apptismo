package com.dperez.apptismo.data


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QuestionsDao {
    @Query("SELECT questions FROM Questions WHERE id = :id")
    suspend fun getQuestions(id: Int): String?// Obtener todas las emociones

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateQuestions(emotion: Questions)// Insertar o actualizar emoción
}
