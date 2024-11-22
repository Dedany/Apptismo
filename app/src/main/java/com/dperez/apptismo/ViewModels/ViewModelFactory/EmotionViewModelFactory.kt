package com.dperez.apptismo.ViewModels.ViewModelFactory


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dperez.apptismo.data.AppDatabase
import com.dperez.apptismo.viewmodels.EmotionViewModel

class EmotionViewModelFactory(private val database: AppDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(EmotionViewModel::class.java)) {
            EmotionViewModel(database) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
