package com.dperez.apptismo


import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.clickable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dperez.apptismo.ViewModels.MainViewModel
import com.dperez.apptismo.data.AppDatabase



@Composable
fun SecondScreen( navController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize(), // La caja ocupa todo el tamaño de la pantalla
        contentAlignment = Alignment.Center
    ) {
        // Fondo con imagen
        Image(
            painter = painterResource(id = R.drawable.fondo), // Asegúrate de poner la imagen en la carpeta res/drawable
            contentDescription = "Fondo",
            modifier = Modifier.fillMaxSize(), // La imagen ocupa toda la pantalla
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp), // Relleno para los cuadros
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center // Centra los elementos verticalmente
        ) {
            // Cuadro "YO" centrado
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.8f) // Hace que ocupe el 80% del ancho de la pantalla
                    .height(120.dp) // Ajusta la altura del cuadro
                    .border(2.dp, Color.White) // Borde blanco alrededor
                    .clickable {
                        navController.navigate("autismScreen")
                    },
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Text(
                    text = "YO",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.Blue // Cambia el color a azul
                    ),
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .wrapContentSize(align = Alignment.Center) // Alinea el texto al centro
                )
            }

            // Espacio de 2 cm (20 dp) entre las dos tarjetas
            Spacer(modifier = Modifier.height(20.dp))

            // Cuadro "Tutor" centrado
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.8f) // Hace que ocupe el 80% del ancho de la pantalla
                    .height(120.dp) // Ajusta la altura del cuadro
                    .border(2.dp, Color.White) // Borde blanco alrededor
                    .clickable {
                        navController.navigate("tutorScreen")
                    },
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Text(
                    text = "Tutor",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.Blue // Cambia el color a azul
                    ),
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .wrapContentSize(align = Alignment.Center) // Alinea el texto al centro
                )
            }
        }
    }
}


