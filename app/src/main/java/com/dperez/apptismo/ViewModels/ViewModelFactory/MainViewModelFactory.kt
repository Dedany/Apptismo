package com.dperez.apptismo.ViewModels.ViewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dperez.apptismo.data.AppDatabase

import MainViewModel

class MainViewModelFactory(private val database: AppDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(database) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
