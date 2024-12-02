package com.dperez.apptismo.data


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QuestionsDao {
    // Obtener todas las preguntas de la base de datos
    @Query("SELECT * FROM Questions")
    suspend fun getQuestions(): List<Questions>

    // Insertar o actualizar una pregunta (se usa para insertar nuevas preguntas)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateQuestions(question: Questions)

    @Query("UPDATE Questions SET response = :response WHERE id = :id")
    suspend fun updateResponse(id: Int, response: String)

    @Query("DELETE FROM Questions")
    suspend fun deleteAllQuestions()

}