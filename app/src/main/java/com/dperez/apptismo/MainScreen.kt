package com.dperez.apptismo


import AppDatabase
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    database: AppDatabase,
    onNavigateToNextScreen: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            // Logo de la app
            Image(
                painter = painterResource(id = R.drawable.autismo_portada), // Asegúrate de tener el logo en res/drawable
                contentDescription = "Logo de Apptismo",
                modifier = Modifier.size(400.dp)
            )

            // Título principal
            Text(
                text = "Bienvenido a Apptismo",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Red
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de texto para introducir el nombre con enfoque y controlador de teclado
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Introduce tu nombre") },
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { focusState ->
                        if (focusState.isFocused) {
                            keyboardController?.show()  // Muestra el teclado cuando el campo recibe foco
                        }
                    }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para guardar el nombre y navegar a la siguiente pantalla
            Button(onClick = {
                if (name.isNotBlank()) {
                    database.insertOrUpdateName(name)  // Guardar o actualizar el nombre en SQLite
                    focusManager.clearFocus()           // Cierra el teclado
                    onNavigateToNextScreen()           // Navegar a la siguiente pantalla
                }
            }) {
                Text(text = "Comenzar")
            }
        }
    }
}
