package com.dperez.apptismo

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dperez.apptismo.ViewModels.MainViewModel
import com.dperez.apptismo.ViewModels.QuestionsViewModel

@Composable
fun QuestionsTutor(navController: NavController,questionsViewModel: QuestionsViewModel) {

    val context = LocalContext.current

    // Obtener la pregunta desde mainViewModel
    val TheQuestions by questionsViewModel.questionsFlow.collectAsState("Pregunta inicial")


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "¿Cómo te sientes hoy?",
            fontSize = 24.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Puedes añadir un botón para volver o navegar a otras pantallas en el futuro
        Button(onClick = {
            navController.popBackStack() // Esta línea hace que vuelvas a la pantalla anterior
        }) {
            Text(text = "Volver")
        }
        Button(onClick = {
            questionsViewModel.insertOrUpdateQuestions("¿Cómo te sientes hoy?")
        }) {
            Text(text = "Guardar Pregunta")
        }
    }
}

