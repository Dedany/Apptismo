package com.dperez.apptismo

import QuestionsFirstScreen
import QuestionsTutor
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dperez.apptismo.data.AppDatabase
import com.dperez.apptismo.ui.theme.ApptismoTheme
import com.dperez.apptismo.viewmodels.MainViewModel
import com.dperez.apptismo.viewmodels.MainViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

    // Se crea el ViewModel y se pasa la base de datos al crear el ViewModel
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory(AppDatabase.getDatabase(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ApptismoTheme {
                // Se pasa el ViewModel al Composable MyApp
                MyApp(mainViewModel = mainViewModel)
            }
        }
    }
}

@Composable
fun MyApp(mainViewModel: MainViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "MainScreen"
    ) {
        composable("MainScreen") {
            MainScreen(
                mainViewModel = mainViewModel,  // Pasar el ViewModel a MainScreen
                onNavigateToNextScreen = {
                    navController.navigate("SecondScreen")
                }
            )
        }
        composable("SecondScreen") {
            SecondScreen(
                navController = navController,
                mainViewModel = mainViewModel  // Pasar el ViewModel a SecondScreen
            )
        }
        composable("autismScreen") {
            AutismScreen(
                navController = navController,
                mainViewModel = mainViewModel  // Pasar el ViewModel a AutismScreen
            )
        }
        composable("QuestionScreen") {
            QuestionScreen(
                navController = navController,
                mainViewModel = mainViewModel  // Pasar el ViewModel a QuestionScreen
            )
        }
        composable("tutorScreen") {
            TutorScreen(
                navController = navController,
                mainViewModel = mainViewModel  // Pasar el ViewModel a TutorScreen
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
    }
}
