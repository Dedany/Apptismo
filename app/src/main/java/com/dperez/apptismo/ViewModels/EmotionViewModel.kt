package com.dperez.apptismo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dperez.apptismo.data.AppDatabase
import com.dperez.apptismo.data.Emotion
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EmotionViewModel(private val database: AppDatabase) : ViewModel() {

    // Estado para manejar la emoción
    private val _emotionFlow = MutableStateFlow("Emoción inicial") // Estado inicial
    val emotionFlow: StateFlow<String> get() = _emotionFlow

    init {
        loadEmotion()
        insertEmotion("Emoción inicial")
    }

    // Función para insertar o actualizar una emoción
    fun insertEmotion(emotion: String) {
        viewModelScope.launch {
            try {
                val newEmotion = Emotion(
                    id = 1,
                    emotion = emotion
                ) // id = 0 permite que Room genere un ID automáticamente
                database.emotionDao().insertOrUpdateEmotion(newEmotion)

            } catch (e: Exception) {
                // Manejo de errores
                e.printStackTrace()
            }
        }
    }

    // Función para cargar la emoción desde la base de datos
    fun loadEmotion() {
        viewModelScope.launch {
            try {
                val emotion =
                    database.emotionDao().getEmotion(id = 1) // Asume que buscas la emoción con id=1
                _emotionFlow.value = emotion ?: "Emoción predeterminada"
            } catch (e: Exception) {
                _emotionFlow.value = "Error al cargar emoción" // Manejo de errores
            }
        }
    }

    // Función para insertar o actualizar la emoción
    fun insertOrUpdateEmotion(emotion: String) {
        viewModelScope.launch {
            try {
                val emotionEntity = Emotion(id = 1, emotion = emotion)
                database.emotionDao().insertOrUpdateEmotion(emotionEntity)
                _emotionFlow.value = emotion // Actualizar el flujo
            } catch (e: Exception) {
                e.printStackTrace() // Manejo de errores
            }
        }
    }
}
