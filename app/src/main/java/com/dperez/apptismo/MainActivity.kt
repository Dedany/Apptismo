package com.dperez.apptismo


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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ApptismoTheme {
                // Pasar los ViewModels al Composable MyApp
                MyApp(
                    mainViewModel = mainViewModel,
                    emotionViewModel = emotionViewModel,
                    nameViewModel = nameViewModel
                )
            }
        }
    }
}

@Composable
fun MyApp(
    mainViewModel: MainViewModel,
    emotionViewModel: EmotionViewModel,
    nameViewModel: NameViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "MainScreen"
    ) {
        composable("MainScreen") {
            MainScreen(
                mainViewModel = mainViewModel,
                onNavigateToNextScreen = {
                    navController.navigate("SecondScreen")
                }
            )
        }
        composable("SecondScreen") {
            SecondScreen(
                navController = navController,
                mainViewModel = mainViewModel
            )
        }
        composable("autismScreen") {
            AutismScreen(
                navController = navController,
                mainViewModel = mainViewModel, // Pasar NameViewModel correctamente
                emotionViewModel = emotionViewModel // Pasar EmotionViewModel correctamente
            )
        }
        composable("QuestionScreen") {
            QuestionScreen(
                navController = navController,
                mainViewModel = mainViewModel
            )
        }
        composable("TutorScreen") {
            TutorScreen(
                navController = navController, mainViewModel = mainViewModel
                // Pasar EmotionViewModel si es necesario
            )
        }
        composable("QuestionsFirstScreen") {
            QuestionsFirstScreen(
                navController = navController
            )
        }
        composable("QuestionsTutor") {
            QuestionsTutor(
                navController = navController
            )
        }
        composable("EmotionScreen") {
            EmotionScreen(
                mainViewModel = mainViewModel,
                navController = navController
            )
        }
    }
}
