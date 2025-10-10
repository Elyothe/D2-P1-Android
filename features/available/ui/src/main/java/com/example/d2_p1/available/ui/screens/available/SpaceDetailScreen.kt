package com.example.d2_p1.available.ui.screens.available


import NavBar
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.d2_p1.available.ui.viewmodels.SpaceDetailViewModel
import com.example.d2_p1.core.data.models.Route
import com.example.d2_p1.core.data.models.Route.spaceDispoDetailsRoute
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpaceDetailScreen(
    navController: NavController,
    spaceId: Int,
    vm: SpaceDetailViewModel = viewModel()
) {
    val uiState by vm.uiState.collectAsState()

    LaunchedEffect(spaceId) {
        vm.loadSpaceDetails(spaceId)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Détail de l'espace") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, "Retour")
                    }
                }
            )
        },
        bottomBar = { NavBar(navController) }
    ) { padding ->
        when {
            uiState.isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            uiState.error != null -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Erreur: ${uiState.error}", color = MaterialTheme.colorScheme.error)
                }
            }
            uiState.space != null -> {
                val space = uiState.space!!
                val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

                Column(
                    modifier = Modifier
                        .padding(padding)
                        .padding(16.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(space.name, style = MaterialTheme.typography.headlineMedium)
                    Text("Capacité: ${space.maxCapacity}")
                    Text("Équipements: ${formatEquipment(space.resources)}")

                    Spacer(Modifier.height(8.dp))
                    Text("Réservations:", style = MaterialTheme.typography.titleMedium)

                    if (space.reservations.isEmpty()) {
                        Text("Aucune réservation.")
                    } else {
                        LazyColumn(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(space.reservations) { reservation ->
                                val startDateTime = LocalDateTime.parse(reservation.startAt)
                                val endDateTime = LocalDateTime.parse(reservation.endAt)

                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                                ) {
                                    Column(modifier = Modifier.padding(16.dp)) {
                                        // Format: "08/10/2025 08:00 - 09:00"
                                        Text(
                                            text = "${startDateTime.toLocalDate().format(dateFormatter)} " +
                                                    "${startDateTime.toLocalTime().format(timeFormatter)} - " +
                                                    "${endDateTime.toLocalTime().format(timeFormatter)}",
                                            style = MaterialTheme.typography.bodyLarge,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Spacer(Modifier.height(4.dp))
                                        Text("Participants: ${reservation.attendeesCount}")
                                    }
                                }
                            }
                        }
                    }

                    Button(
                        onClick = {
                            navController.navigate(spaceDispoDetailsRoute(space.id))
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Text("Nouvelle réservation")
                    }
                }
            }
        }
    }
}

@Composable
private fun formatEquipment(resources: List<String>): String {
    return resources.joinToString(", ") { resource ->
        when (resource.lowercase()) {
            "vidéoprojecteur" -> "📽️ Vidéoprojecteur"
            "tableau blanc" -> "📝 Tableau blanc"
            "télévision", "tv" -> "📺 Télévision"
            "enceinte", "haut-parleur" -> "🔊 Enceinte"
            "grand écran" -> "🖥️ Grand écran"
            "télé" -> "📺 Télévision"
            "prises" -> "⚡ Prises électriques"
            else -> resource
        }
    }
}