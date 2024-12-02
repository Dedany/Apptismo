package com.dperez.apptismo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dperez.apptismo.viewmodels.NameViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    nameViewModel: NameViewModel,
    onNavigateToNextScreen: () -> Unit
) {
    var name by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            // Agregar una imagen al principio
            Image(
                painter = painterResource(id = R.drawable.autismo_portada
                ), // Cambia por tu recurso de imagen
                contentDescription = "Logo de la aplicación",
                modifier = Modifier
                    .size(190.dp)
                    .padding(bottom = 16.dp)
            )

            // Texto de instrucciones
            Text("Introduce tu nombre")

            // Campo de texto para el nombre
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Introduce tu nombre") }
                
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para continuar
            Button(onClick = {
                if (name.isNotBlank()) {
                    // Llamar al ViewModel para insertar el nombre
                    nameViewModel.insertOrUpdateName(name)
                    onNavigateToNextScreen()
                }
            }) {
                Text(text = "Comenzar")
            }
        }
    }
}
