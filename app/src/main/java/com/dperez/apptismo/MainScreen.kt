package com.dperez.apptismo


import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dperez.apptismo.ViewModels.MainViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    mainViewModel: MainViewModel,
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
            Text("Introduce tu nombre")

            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Introduce tu nombre") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                if (name.isNotBlank()) {
                    // Llamar al ViewModel para insertar el nombre
                    mainViewModel.insertOrUpdateName(name)
                    onNavigateToNextScreen()
                }
            }) {
                Text(text = "Comenzar")
            }
        }
    }
}
