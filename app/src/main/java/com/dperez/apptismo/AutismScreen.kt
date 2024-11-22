package com.dperez.apptismo



import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

import com.dperez.apptismo.data.AppDatabase
import com.dperez.apptismo.viewmodels.EmotionViewModel

import androidx.compose.material.icons.automirrored.filled.ArrowBack
import com.dperez.apptismo.ViewModels.MainViewModel

@Composable
fun AutismScreen(
    navController: NavController,
    mainViewModel: MainViewModel, // Usamos NameViewModel para obtener el nombre
    emotionViewModel: EmotionViewModel // Usamos EmotionViewModel para manejar emociones
) {
    val context = LocalContext.current

    // Obtener el nombre desde NameViewModel
    val userName by mainViewModel.nameFlow.collectAsState("Usuario")

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
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
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
                    .clickable {
                        val emotion = "Feliz"
                        emotionViewModel.insertEmotion(emotion)
                        Toast.makeText(context, "¡Te sientes feliz!", Toast.LENGTH_SHORT).show()
                    }
            )
            Image(
                painter = painterResource(id = R.drawable.neutral),
                contentDescription = "Cara Neutral",
                modifier = Modifier
                    .size(100.dp)
                    .clickable {
                        val emotion = "Neutral"
                        emotionViewModel.insertEmotion(emotion)
                        Toast.makeText(context, "Te sientes neutral.", Toast.LENGTH_SHORT).show()
                    }
            )
            Image(
                painter = painterResource(id = R.drawable.triste),
                contentDescription = "Cara Triste",
                modifier = Modifier
                    .size(100.dp)
                    .clickable {
                        val emotion = "Triste"
                        emotionViewModel.insertEmotion(emotion)
                        Toast.makeText(context, "Te sientes triste.", Toast.LENGTH_SHORT).show()
                    }
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
                    .clickable {
                        val emotion = "Aburrido"
                        emotionViewModel.insertEmotion(emotion)
                        Toast.makeText(context, "Te sientes aburrido.", Toast.LENGTH_SHORT).show()
                    }
            )
            Image(
                painter = painterResource(id = R.drawable.enfado),
                contentDescription = "Cara Enfado",
                modifier = Modifier
                    .size(100.dp)
                    .clickable {
                        val emotion = "Enfado"
                        emotionViewModel.insertEmotion(emotion)
                        Toast.makeText(context, "Te sientes enfadado.", Toast.LENGTH_SHORT).show()
                    }
            )
            Image(
                painter = painterResource(id = R.drawable.llanto),
                contentDescription = "Cara Llanto",
                modifier = Modifier
                    .size(100.dp)
                    .clickable {
                        val emotion = "Llanto"
                        emotionViewModel.insertEmotion(emotion)
                        Toast.makeText(context, "Tienes ganas de llorar.", Toast.LENGTH_SHORT).show()
                    }
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


        // Texto de ayuda con ícono
        Text(
            text = "❔",
            fontSize = 48.sp,
            modifier = Modifier
                .padding(top = 16.dp)
                .clickable {
                    navController.navigate("QuestionsFirstScreen")
                }
        )
    }
}

@Composable
fun EmotionImage(
    resourceId: Int,
    description: String,
    emotion: String,
    emotionViewModel: EmotionViewModel,
    context: android.content.Context
) {
    Image(
        painter = painterResource(id = resourceId),
        contentDescription = description,
        modifier = Modifier
            .size(100.dp)
            .clickable {
                emotionViewModel.insertEmotion(emotion)
                Toast.makeText(context, "¡Te sientes $emotion!", Toast.LENGTH_SHORT).show()
            }
    )
}

