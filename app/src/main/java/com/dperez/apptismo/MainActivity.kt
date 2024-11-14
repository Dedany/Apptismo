package com.dperez.apptismo

import AppDatabase
import QuestionsFirstScreen
import QuestionsTutor
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.composableLambda
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.dperez.apptismo.ui.theme.ApptismoTheme


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Crear la instancia de AppDatabase
        val database = AppDatabase(this)

        setContent {
            ApptismoTheme {
                MyApp(database)
            }
        }
    }
}


@Composable
fun MyApp(database: AppDatabase) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "MainScreen"
    ) {
        composable("MainScreen") {
            MainScreen(
                database = database,  // Pasar database a MainScreen
                onNavigateToNextScreen = {
                    navController.navigate("SecondScreen")
                }
            )
        }
        composable("SecondScreen") {
            SecondScreen(
                navController = navController,
                database = database  // Pasar database a SecondScreen
            )
        }
        composable("autismScreen") {
            AutismScreen(
                navController = navController,
                database = database  // Pasar database a AutismScreen
            )
        }
        composable("QuestionScreen") {
            QuestionScreen(
                navController = navController,
                database = database  // Pasar database a QuestionScreen
            )
        }
        composable("tutorScreen") {
            TutorScreen(
                navController = navController,
                database = database  // Pasar database a TutorScreen
            )
        }
        composable("QuestionsFirstScreen") {
            QuestionsFirstScreen(
                navController = navController,
                // database = database  // Pasar database a QuestionsFirstScreen
            )
        }
        composable("QuestionsTutor") {
            QuestionsTutor(
                navController = navController
            )
        }
    }
}
