import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.wear.compose.navigation.currentBackStackEntryAsState
import androidx.compose.material3.Icon
import com.example.d2_p1.core.data.models.Route


@Composable
fun NavBar(navController: NavController) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry.value?.destination?.route

    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Accueil") },
            label = { Text("Home") },
            selected = currentRoute == "home",
            onClick = { navController.navigate("home_screen") }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Default.DateRange, contentDescription = "Réservation") },
            label = { Text("Réservation") },
            selected = currentRoute == "reservation",
            onClick = { navController.navigate("listspace_screen") }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Default.Search, contentDescription = "Rechercher") },
            label = { Text("Recherche") },
            selected = currentRoute == Route.SearchSpace,
            onClick = { navController.navigate(Route.SearchSpace) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.AddCircle, contentDescription = "Disponibles") },
            label = { Text("Disponibles") },
            selected = currentRoute == Route.AvailableSpaces,
            onClick = { navController.navigate(Route.AvailableSpaces) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Profil") },
            label = { Text("Profil") },
            selected = currentRoute == "profil",
            onClick = { navController.navigate("profil") }
        )

    }
}
