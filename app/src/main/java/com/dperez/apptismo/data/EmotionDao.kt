package com.dperez.apptismo.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RewriteQueriesToDropUnusedColumns

@Dao
interface EmotionDao {
    @Query("SELECT emotion FROM Emotion WHERE id = :id")
    suspend fun getEmotion(id: Int): String?// Obtener todas las emociones

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateEmotion(emotion: Emotion)// Insertar o actualizar emoción
}
