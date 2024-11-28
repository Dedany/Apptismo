package com.dperez.apptismo

import QuestionsViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun QuestionsFirstScreen(navController: NavController, questionsViewModel: QuestionsViewModel) {
    val questionsList by questionsViewModel.allQuestionsFlow.collectAsState(emptyList())
    val answersMap = remember { mutableStateMapOf<Int, String>() } // Usar mutableStateMapOf para observar cambios

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Responde las Preguntas",
            fontSize = 24.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(questionsList) { question ->
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = question.questions,
                        fontSize = 18.sp,
                        color = Color.DarkGray
                    )
                    OutlinedTextField(
                        value = answersMap[question.id] ?: "", // Obtener la respuesta del mapa
                        onValueChange = { answersMap[question.id] = it }, // Actualizar la respuesta en el mapa
                        label = { Text("Escribe tu respuesta") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Button(onClick = {
                        val currentAnswer = answersMap[question.id]
                        if (!currentAnswer.isNullOrBlank()) {
                            questionsViewModel.updateAnswer(question.id, currentAnswer) // Guardar respuesta en la base de datos
                            answersMap[question.id] = "" // Limpiar después de guardar
                        }
                    }) {
                        Text(text = "Guardar Respuesta")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text(text = "Volver")
        }
    }
}
