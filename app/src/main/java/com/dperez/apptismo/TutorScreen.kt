package com.dperez.apptismo

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun TutorScreen(navController: NavController) {
    // Obtener el contexto
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Cuadro de información
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(width = 2.dp, color = Color.Gray)
                .background(color = Color(0xFFE3F2FD)) // Fondo color azul claro
                .padding(16.dp)
        ) {
            Text(
                text = "El trastorno del espectro del autismo (TEA) es una condición del neurodesarrollo " +
                        "que afecta a la configuración del sistema nervioso y al funcionamiento cerebral. " +
                        "Se caracteriza por dificultades en la comunicación, interacción social, " +
                        "y flexibilidad del pensamiento y la conducta.",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(20.dp)) // Espacio entre los elementos

        // Texto "estado de" centrado
        Text(
            text = "Estado de salud",
            fontSize = 22.sp,
            color = Color.Blue,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(20.dp)) // Espacio adicional

        // Texto "Preguntas" con función de clic
        Text(
            text = "Preguntas",
            fontSize = 32.sp,
            color = Color.Blue,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .clickable {
                    Toast.makeText(context, "Tengo unas preguntas para ti", Toast.LENGTH_SHORT).show()
                }
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TutorScreenPreview() {
    TutorScreen(navController = rememberNavController()) // Pasar el navController a la vista previa
}
