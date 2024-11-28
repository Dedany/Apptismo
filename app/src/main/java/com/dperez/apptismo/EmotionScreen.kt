package com.dperez.apptismo

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dperez.apptismo.ViewModels.MainViewModel
import com.dperez.apptismo.viewmodels.EmotionViewModel


@Composable
fun EmotionScreen(
    emotionViewModel: EmotionViewModel,
    navController: NavController // Pasamos NavController para navegar
) {
    val emotion by emotionViewModel.emotionFlow.collectAsState(initial = "Emoción inicial")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Botón de retroceso
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Volver",
                tint = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar la emoción actual
        Text(
            text = "Hoy está: $emotion",
            fontSize = 24.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
    }
}
