package com.dperez.apptismo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dperez.apptismo.data.AppDatabase
import com.dperez.apptismo.data.Name

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NameViewModel(private val database: AppDatabase) : ViewModel() {

    private val _nameFlow = MutableStateFlow("Usuario") // Estado inicial
    val nameFlow: StateFlow<String> get() = _nameFlow

    init {
        loadName()
    }

    private fun loadName() {
        viewModelScope.launch {
            try {
                val name = database.nameDao().getName(id = 1) // Asume un único usuario con id=1
                _nameFlow.value = name?.name ?: "Usuario"
            } catch (e: Exception) {
                _nameFlow.value = "Usuario" // Manejo de errores
            }
        }
    }

    fun insertOrUpdateName(name: String) {
        viewModelScope.launch {
            try {
                val nameEntity = Name(id = 1, name = name)
                database.nameDao().insertOrUpdateName(nameEntity)
                _nameFlow.value = name // Actualizar el flujo
            } catch (e: Exception) {
                // Manejo de errores
            }
        }
    }
}
