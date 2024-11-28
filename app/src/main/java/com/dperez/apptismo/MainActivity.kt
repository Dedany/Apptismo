package com.dperez.apptismo


import QuestionsViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dperez.apptismo.ViewModels.MainViewModel

import com.dperez.apptismo.ViewModels.ViewModelFactory.EmotionViewModelFactory
import com.dperez.apptismo.ui.theme.ApptismoTheme

import com.dperez.apptismo.viewmodels.EmotionViewModel
import com.dperez.apptismo.ViewModels.ViewModelFactory.MainViewModelFactory

import com.dperez.apptismo.ViewModels.ViewModelFactory.NameViewModelFactory
import com.dperez.apptismo.ViewModels.ViewModelFactory.QuestionsViewModelFactory
import com.dperez.apptismo.data.AppDatabase

import com.dperez.apptismo.viewmodels.NameViewModel


@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

    // Se crea el MainViewModel
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory(AppDatabase.getDatabase(this))
    }

    // Se crea el EmotionViewModel
    private val emotionViewModel: EmotionViewModel by viewModels {
        EmotionViewModelFactory(AppDatabase.getDatabase(this))
    }

    // Se crea el NameViewModel
    private val nameViewModel: NameViewModel by viewModels {
        NameViewModelFactory(AppDatabase.getDatabase(this))
    }

    private val questionsViewModel: QuestionsViewModel by viewModels {
        QuestionsViewModelFactory(AppDatabase.getDatabase(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ApptismoTheme {
                // Pasar los ViewModels al Composable MyApp
                MyApp(
                    emotionViewModel = emotionViewModel,
                    nameViewModel = nameViewModel,
                    questionsViewModel = questionsViewModel
                )
            }
        }
    }
}

@Composable
fun MyApp(

    emotionViewModel: EmotionViewModel,
    nameViewModel: NameViewModel,
    questionsViewModel: QuestionsViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "MainScreen"
    ) {
        composable("MainScreen") {
            MainScreen(
                nameViewModel = nameViewModel,
                onNavigateToNextScreen = {
                    navController.navigate("SecondScreen")
                }
            )
        }
        composable("SecondScreen") {
            SecondScreen(
                navController = navController,

            )
        }
        composable("autismScreen") {
            AutismScreen(
                navController = navController,
                nameViewModel =   nameViewModel, // Pasar NameViewModel correctamente
                emotionViewModel = emotionViewModel // Pasar EmotionViewModel correctamente
            )
        }
        composable("QuestionScreen") {
            QuestionScreen(
                navController = navController,
               nameViewModel = nameViewModel
            )
        }
        composable("TutorScreen") {
            TutorScreen(
                navController = navController, nameViewModel = nameViewModel
                // Pasar EmotionViewModel si es necesario
            )
        }
        composable("QuestionsFirstScreen") {
            QuestionsFirstScreen(
                navController = navController,
                questionsViewModel = questionsViewModel
            )
        }
        composable("QuestionsTutor") {
            QuestionsTutor(
                navController = navController,
                questionsViewModel = questionsViewModel
            )
        }
        composable("EmotionScreen") {
            EmotionScreen(
                emotionViewModel = emotionViewModel,
                navController = navController
            )
        }
    }
}
