package com.dperez.apptismo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dperez.apptismo.ui.theme.ApptismoTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApptismoTheme {
                MyApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    val navController = rememberNavController()

    // Configuración del NavHost para gestionar la navegación
    NavHost(
        navController = navController,
        startDestination = "MainScreen"
    ) {
        composable("MainScreen") {
            MainScreen(onNavigateToNextScreen = {
                navController.navigate("SecondScreen")
            })
        }
        composable("SecondScreen") {
            SecondScreen(navController = navController)
        }
        composable("autismScreen") {
            AutismScreen(navController = navController) // Aquí debes definir tu pantalla AutismScreen
        }
        composable("QuestionScreen") {
            AutismScreen(navController = navController) // Aquí debes definir tu pantalla AutismScreen
        }
        composable("tutorScreen") {
            TutorScreen(navController = navController) // Aquí debes definir tu pantalla TutorScreen
        }
    }
}
