package com.dperez.apptismo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(onNavigateToNextScreen: () -> Unit) {
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

            // Botón para navegar a la siguiente pantalla
            Button(onClick = { onNavigateToNextScreen() }) {
                Text(text = "Comenzar")
            }
        }
    }
}
