package com.dperez.apptismo.ViewModels.ViewModelFactory



import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dperez.apptismo.ViewModels.QuestionsViewModel
import com.dperez.apptismo.data.AppDatabase

class QuestionsViewModelFactory(private val database: AppDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(QuestionsViewModel::class.java)) {
           QuestionsViewModel(database) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
