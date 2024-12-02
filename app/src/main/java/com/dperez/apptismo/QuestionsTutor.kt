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
    val allQuestions by questionsViewModel.allQuestionsFlow.collectAsState(emptyList())
    var newQuestion by remember { mutableStateOf("") } // Estado para la nueva pregunta

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Preguntas y Respuestas",
            fontSize = 24.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de entrada para nueva pregunta
        OutlinedTextField(
            value = newQuestion,
            onValueChange = { newQuestion = it },
            label = { Text("Escribe una nueva pregunta") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Botón para guardar la nueva pregunta
        Button(onClick = {
            if (newQuestion.isNotBlank()) {
                questionsViewModel.insertQuestion(newQuestion) // Guardar la nueva pregunta
                newQuestion = "" // Limpiar el campo después de guardar
            }
        }) {
            Text(text = "Añadir Pregunta")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de preguntas y respuestas
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(allQuestions) { question ->
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Pregunta: ${question.questions}",
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                    Text(
                        text = "Respuesta: ${question.response ?: "Sin respuesta"}",
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                }
            }
        }
        Button(onClick = { navController.popBackStack() }) {
            Text(text = "Volver")
        }
        // Botón para borrar todas las preguntas
        Button(onClick = {
            questionsViewModel.deleteAllQuestions() // Llama al método para borrar
        }) {
            Text(text = "Borrar Todas las Preguntas")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text(text = "Volver")
        }

    }
}
