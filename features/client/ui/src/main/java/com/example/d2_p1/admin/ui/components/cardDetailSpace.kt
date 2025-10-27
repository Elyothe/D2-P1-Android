

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.d2_p1.core.data.models.Space
import com.example.d2_p1.features.core.ui.R

@Composable
fun DetailSpaceCard(space: Space) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // IMAGE DE LA SALLE
            Image(
                painter = painterResource(id = R.drawable.default_room),
                contentDescription = "Photo de la salle",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = space.name,
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = "Catégorie : ${space.category}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Capacité : ${space.maxCapacity} personnes",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Description :",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = space.description,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Ressources :",
                style = MaterialTheme.typography.titleMedium
            )
            space.resources.forEach {
                Text("- $it", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}