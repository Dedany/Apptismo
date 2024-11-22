import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dperez.apptismo.viewmodels.EmotionViewModel
import MainViewModel


@Composable
fun EmotionScreen(mainViewModel: MainViewModel,emotionViewModel: EmotionViewModel) {
    // Obtener el flujo de emociones desde el ViewModel
    val emotion by mainViewModel.emotionFlow.collectAsState(initial = "Emoción inicial")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Hoy está: $emotion",
            fontSize = 24.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
    }
}
