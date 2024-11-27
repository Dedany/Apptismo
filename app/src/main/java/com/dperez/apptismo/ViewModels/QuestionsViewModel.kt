package com.dperez.apptismo.ViewModels





import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dperez.apptismo.data.AppDatabase
import com.dperez.apptismo.data.Questions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class QuestionsViewModel (private val database: AppDatabase) : ViewModel() {

    private val _questionsFlow = MutableStateFlow("Pregunta inicial") // Estado inicial
    val questionsFlow: StateFlow<String> get() = _questionsFlow



    fun insertOrUpdateQuestions(questionText: String) {
        viewModelScope.launch {
            try {
                val questionsEntity = Questions(id = 1, questions = questionText)
                database.questionsDao().insertOrUpdateQuestions(questionsEntity)
                _questionsFlow.value = questionText // Actualizar el flujo
            } catch (e: Exception) {
                e.printStackTrace() // Manejo de errores
            }
        }
    }
    fun loadQuestions() {
        viewModelScope.launch {
            try {
                val questions = database.questionsDao().getQuestions(id = 1) // Asume que buscas la emoción con id=1
                _questionsFlow.value = questions ?: "pregunta predeterminada"
            } catch (e: Exception) {
                _questionsFlow.value = "Error al cargar pregunta" // Manejo de errores
            }
        }
    }
}
