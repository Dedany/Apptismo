package com.dperez.apptismo.ViewModels.ViewModelFactory

import NameViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dperez.apptismo.data.AppDatabase

class NameViewModelFactory(private val database: AppDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NameViewModel::class.java)) {
            return NameViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
