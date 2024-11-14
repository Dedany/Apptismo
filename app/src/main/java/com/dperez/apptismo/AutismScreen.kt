package com.dperez.apptismo

import AppDatabase
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun AutismScreen(
    navController: NavController,
    database: AppDatabase
) {
    val context = LocalContext.current
    val userName by database.userNameFlow.collectAsState("Usuario")

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
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Volver",
                tint = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Título de la pantalla con el nombre del usuario
        Text(
            text = "Cómo estás, $userName",
            modifier = Modifier.padding(bottom = 20.dp),
            color = Color.Black,
            fontSize = 24.sp
        )

        // Primera fila de caras (3 imágenes)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painter = painterResource(id = R.drawable.feliz),
                contentDescription = "Cara Alegre",
                modifier = Modifier
                    .size(100.dp)
                    .clickable { Toast.makeText(context, "¡Te sientes feliz!", Toast.LENGTH_SHORT).show() }
            )
            Image(
                painter = painterResource(id = R.drawable.neutral),
                contentDescription = "Cara Neutral",
                modifier = Modifier
                    .size(100.dp)
                    .clickable { Toast.makeText(context, "Te sientes neutral.", Toast.LENGTH_SHORT).show() }
            )
            Image(
                painter = painterResource(id = R.drawable.triste),
                contentDescription = "Cara Triste",
                modifier = Modifier
                    .size(100.dp)
                    .clickable { Toast.makeText(context, "Te sientes triste.", Toast.LENGTH_SHORT).show() }
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        // Segunda fila de caras (3 imágenes)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 50.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painter = painterResource(id = R.drawable.aburrido),
                contentDescription = "Cara Aburrido",
                modifier = Modifier
                    .size(100.dp)
                    .clickable { Toast.makeText(context, "Te sientes aburrido.", Toast.LENGTH_SHORT).show() }
            )
            Image(
                painter = painterResource(id = R.drawable.enfado),
                contentDescription = "Cara Enfado",
                modifier = Modifier
                    .size(100.dp)
                    .clickable { Toast.makeText(context, "Te sientes enfadado.", Toast.LENGTH_SHORT).show() }
            )
            Image(
                painter = painterResource(id = R.drawable.llanto),
                contentDescription = "Cara Llanto",
                modifier = Modifier
                    .size(100.dp)
                    .clickable { Toast.makeText(context, "Tienes ganas de llorar.", Toast.LENGTH_SHORT).show() }
            )
        }

        Spacer(modifier = Modifier.height(60.dp))

        // Texto de "Preguntas"
        Text(
            text = "Preguntas",
            fontSize = 32.sp,
            color = Color.Blue,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable {
               navController.navigate("QuestionsFirstScreen")
            }
        )

        Text(
            text = "❔",
            fontSize = 48.sp,
            modifier = Modifier.padding(top = 16.dp).clickable {
              navController.navigate("QuestionsFirstScreen")
            }
        )
    }
}
