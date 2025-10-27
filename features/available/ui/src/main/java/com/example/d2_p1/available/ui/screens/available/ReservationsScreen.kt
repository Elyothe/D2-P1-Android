package com.example.d2_p1.available.ui.screens.available


import NavBar
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.d2_p1.available.ui.viewmodels.ReservationsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservationsScreen(
    spaceId: Int,
    navController: NavController,
    vm: ReservationsViewModel = viewModel()
) {
    val uiState by vm.uiState.collectAsState()

    LaunchedEffect(spaceId) {
        vm.loadReservations(spaceId)
    }

    Scaffold(
        bottomBar = { NavBar(navController) },
        topBar = { TopAppBar(title = { Text("Réservations de la salle $spaceId") }) }
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
            uiState.reservations.isEmpty() -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Aucune réservation trouvée.")
                }
            }
            else -> {
                LazyColumn(modifier = Modifier.padding(padding)) {
                    items(uiState.reservations) { reservation ->
                        Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text("ID: ${reservation.id}")
                                Text("Heure: ${reservation.startAt} → ${reservation.endAt}")
                                Text("Participants: ${reservation.attendeesCount}")
                            }
                        }
                    }
                }
            }
        }
    }
}