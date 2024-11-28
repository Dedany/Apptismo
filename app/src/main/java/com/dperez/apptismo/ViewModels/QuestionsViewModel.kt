import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dperez.apptismo.data.AppDatabase
import com.dperez.apptismo.data.Questions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class QuestionsViewModel(private val database: AppDatabase) : ViewModel() {
    // Flujo para manejar todas las preguntas
    private val _allQuestionsFlow = MutableStateFlow<List<Questions>>(emptyList())
    val allQuestionsFlow: StateFlow<List<Questions>> get() = _allQuestionsFlow

    init {
        loadAllQuestions()

    }

    // Cargar todas las preguntas desde la base de datos
    private fun loadAllQuestions() {
        viewModelScope.launch {
            try {
                val questions = database.questionsDao().getQuestions()
                _allQuestionsFlow.update { questions }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Insertar una nueva pregunta en la base de datos
    fun insertQuestion(questionText: String) {
        viewModelScope.launch {
            try {
                val newQuestion = Questions(questions = questionText)
                database.questionsDao().insertOrUpdateQuestions(newQuestion)
                // Recargar todas las preguntas después de la inserción
                loadAllQuestions()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    // Actualizar una respuesta
    fun updateAnswer(id: Int, answer: String) {
        viewModelScope.launch {
            try {
                database.questionsDao().updateResponse(id, answer)
                loadAllQuestions() // Recargar después de actualizar
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
