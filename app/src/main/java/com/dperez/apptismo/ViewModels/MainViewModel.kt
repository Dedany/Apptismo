package com.dperez.apptismo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dperez.apptismo.data.AppDatabase
import com.dperez.apptismo.data.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val database: AppDatabase) : ViewModel() {

    // Estado para manejar el nombre del usuario
    private val _userNameFlow = MutableStateFlow<String>("Usuario")
    val userNameFlow: StateFlow<String> get() = _userNameFlow

    init {
        // Cargar el nombre del usuario cuando se inicializa el ViewModel
        loadUserName()
    }

    // Función para cargar el nombre del usuario desde la base de datos
    private fun loadUserName() {
        viewModelScope.launch {
            val name = database.userDao().getName() // Aquí se obtiene el nombre desde la base de datos
            _userNameFlow.value = name ?: "Usuario"
        }
    }

    // Función para insertar o actualizar el nombre del usuario
    fun insertOrUpdateName(name: String) {
        viewModelScope.launch {
            database.userDao().insertOrUpdateName(User(name)) // Aquí se inserta o actualiza el nombre en la base de datos
            _userNameFlow.value = name // Actualizar el valor en el flujo
        }
    }
}
