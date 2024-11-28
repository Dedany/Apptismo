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


    //Estado para manejar las preguntas

    init {
        // Cargar los datos iniciales cuando se inicializa el ViewModel
        loadName()


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


    }



