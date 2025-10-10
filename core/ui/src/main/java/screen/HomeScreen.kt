package screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
    onNavigateToCreate: () -> Unit,
    onNavigateToModify: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Bienvenue sur l'écran d'accueil")

        Button(onClick = onNavigateToCreate) {
            Text("Créer un espace")
        }

        Button(onClick = onNavigateToModify) {
            Text("Modifier un espace")
        }
    }
}