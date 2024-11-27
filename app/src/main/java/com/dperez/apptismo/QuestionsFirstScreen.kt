package com.dperez.apptismo

import QuestionsViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun QuestionsFirstScreen(navController: NavController, questionsViewModel: QuestionsViewModel) {
    // Obtener la lista de preguntas desde el ViewModel
    val questionsList by questionsViewModel.allQuestionsFlow.collectAsState(emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Título de la pantalla
        Text(
            text = "Preguntas Guardadas",
            fontSize = 24.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Mostrar la lista de preguntas
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(questionsList) { question ->
                Text(
                    text = question.questions, // Mostrar el texto de la pregunta
                    fontSize = 18.sp,
                    color = Color.DarkGray
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para volver a la pantalla anterior
        Button(onClick = { navController.popBackStack() }) {
            Text(text = "Volver")
        }
    }
}
