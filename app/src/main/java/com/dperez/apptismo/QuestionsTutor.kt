package com.dperez.apptismo

import QuestionsViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun QuestionsTutor(navController: NavController, questionsViewModel: QuestionsViewModel) {
    // Obtenemos la lista de preguntas desde el flujo de preguntas en QuestionsViewModel
    val allQuestions by questionsViewModel.allQuestionsFlow.collectAsState(initial = emptyList())

    // Estado local para capturar la pregunta del usuario
    var newQuestionText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Lista de preguntas",
            fontSize = 24.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar las preguntas en un LazyColumn
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(allQuestions) { question ->
                Text(
                    text = question.questions,
                    fontSize = 18.sp,
                    color = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de texto para escribir una nueva pregunta
        OutlinedTextField(
            value = newQuestionText,
            onValueChange = { newQuestionText = it },
            label = { Text("Escribe una nueva pregunta") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para guardar la nueva pregunta
        Button(onClick = {
            if (newQuestionText.isNotBlank()) {
                questionsViewModel.insertQuestion(newQuestionText)
                newQuestionText = "" // Limpiar el campo después de guardar
            }
        }) {
            Text(text = "Añadir pregunta")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para regresar a la pantalla anterior
        Button(onClick = {
            navController.popBackStack()
        }) {
            Text(text = "Volver")
        }
    }
}
