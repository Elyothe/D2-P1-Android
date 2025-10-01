package component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import navigation.Screen


@Composable
fun CustomNavigationBar(
    onNavigate: (String) -> Unit
) {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("Accueil", "Réservation", "Profil")

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clip(RoundedCornerShape(24.dp))
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(24.dp)
            )
            .background(Color(0xFFE0E0E0))
    ) {
        NavigationBar(
            modifier = Modifier.fillMaxWidth(),
            containerColor = Color.Transparent,
            tonalElevation = 0.dp
        ) {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    icon = {
                        when (index) {
                            0 -> Icon(Icons.Default.List, contentDescription = item)
                            1 -> Icon(Icons.Default.DateRange, contentDescription = item)
                            2 -> Icon(Icons.Default.Person, contentDescription = item)
                            else -> Icon(Icons.Default.List, contentDescription = item)
                        }
                    },
                    label = { Text(item) },
                    selected = selectedItem == index,
                    onClick = {
                        selectedItem = index
                        when (index) {
                            0 -> onNavigate(Screen.HomeScreen.route)
                            1 -> onNavigate(Screen.CreateSpaceScreen.route)
                            2 -> println("TODO: Navigation vers profil")
                        }
                    }
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewCustomNavigationBar() {
    CustomNavigationBar(onNavigate = {})
}
