package com.dperez.apptismo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dperez.apptismo.data.AppDatabase
import com.dperez.apptismo.data.Emotion
import kotlinx.coroutines.launch

class EmotionViewModel(private val database: AppDatabase) : ViewModel() {

    // Función para insertar o actualizar una emoción
    fun insertEmotion(emotion: String) {
        viewModelScope.launch {
            try {
                val newEmotion = Emotion(id = 0, emotion = emotion) // id = 0 permite que Room genere un ID automáticamente
                database.emotionDao().insertOrUpdateEmotion(newEmotion)
            } catch (e: Exception) {
                // Manejo de errores
                e.printStackTrace()
            }
        }
    }
}
