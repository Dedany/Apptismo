package com.dperez.apptismo.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dperez.apptismo.data.AppDatabase
import com.dperez.apptismo.data.Emotion
import com.dperez.apptismo.data.Name
import com.dperez.apptismo.data.Questions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val database: AppDatabase) : ViewModel() {

    // Estado para manejar el nombre del usuario
    private val _nameFlow = MutableStateFlow("Usuario") // Estado inicial
    val nameFlow: StateFlow<String> get() = _nameFlow

    // Estado para manejar la emoción
    private val _emotionFlow = MutableStateFlow("Emoción inicial") // Estado inicial
    val emotionFlow: StateFlow<String> get() = _emotionFlow

    //Estado para manejar las preguntas
    private val _questionsFlow = MutableStateFlow("Pregunta inicial") // Estado inicial
    val questionsFlow: StateFlow<String> get() = _questionsFlow

    init {
        // Cargar los datos iniciales cuando se inicializa el ViewModel
        loadName()
        loadEmotion()

    }

    // Función para cargar el nombre desde la base de datos
    private fun loadName() {
        viewModelScope.launch {
            try {
                val nameEntity = database.nameDao().getName(id = 1) // Obtener el objeto Name
                _nameFlow.value = nameEntity?.name ?: "Usuario" // Usar la propiedad `name`
            } catch (e: Exception) {
                _nameFlow.value = "Usuario" // Valor predeterminado en caso de error
            }
        }
    }

    // Función para insertar o actualizar el nombre
    fun insertOrUpdateName(name: String) {
        viewModelScope.launch {
            try {
                val nameEntity = Name(id = 1, name = name) // id=1 para un único usuario
                database.nameDao().insertOrUpdateName(nameEntity)
                _nameFlow.value = name // Actualizar el flujo
            } catch (e: Exception) {
                e.printStackTrace() // Manejo de errores
            }
        }
    }

    // Función para cargar la emoción desde la base de datos
     fun loadEmotion() {
        viewModelScope.launch {
            try {
                val emotion = database.emotionDao().getEmotion(id = 1) // Asume que buscas la emoción con id=1
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
