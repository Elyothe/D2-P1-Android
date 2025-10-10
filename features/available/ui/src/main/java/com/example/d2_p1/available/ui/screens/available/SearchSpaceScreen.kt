package com.example.d2_p1.available.ui.screens.available

import NavBar
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.d2_p1.available.ui.viewmodels.SearchSpaceViewModel
import com.example.d2_p1.core.data.models.Route.dailyAvailabilityRoute
import com.example.d2_p1.core.data.models.Route.spaceDispoDetailsRoute
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchSpaceScreen(
    navController: NavController,
    vm: SearchSpaceViewModel = viewModel()
) {
    val uiState by vm.uiState.collectAsState()
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    // Afficher un toast si nécessaire
    LaunchedEffect(uiState.showToast) {
        if (uiState.showToast) {
            Toast.makeText(
                context,
                uiState.toastMessage,
                Toast.LENGTH_LONG
            ).show()
            vm.resetToast()
        }
    }

    Scaffold(
        bottomBar = { NavBar(navController) },
        topBar = { TopAppBar(title = { Text("Rechercher un espace") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = uiState.query,
                onValueChange = { vm.onQueryChange(it) },
                label = { Text("Nom ou catégorie") },
                modifier = Modifier.fillMaxWidth()
            )

            Text("Capacité minimale : ${uiState.capacity.toInt()}")
            Slider(
                value = uiState.capacity,
                onValueChange = { vm.onCapacityChange(it) },
                valueRange = 1f..30f
            )

            Spacer(Modifier.height(8.dp))
            Row {
                FilterCheckbox(
                    label = "Avec projecteur",
                    checked = uiState.projector,
                    onCheckedChange = { vm.onProjectorChange(it) }
                )
                Spacer(Modifier.width(12.dp))
                FilterCheckbox(
                    label = "Avec tableau blanc",
                    checked = uiState.board,
                    onCheckedChange = { vm.onBoardChange(it) }
                )
            }

            Row {
                FilterCheckbox(
                    label = "Avec télévision",
                    checked = uiState.tele,
                    onCheckedChange = { vm.onTeleChange(it) }
                )
                Spacer(Modifier.width(12.dp))
                FilterCheckbox(
                    label = "Avec enceinte",
                    checked = uiState.enceinte,
                    onCheckedChange = { vm.onEnceinteChange(it) }
                )
            }

            Spacer(Modifier.height(16.dp))
            Button(
                onClick = { vm.search() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Rechercher")
            }

            Spacer(Modifier.height(16.dp))

            when {
                uiState.isLoading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                uiState.error != null -> {
                    Text("Erreur: ${uiState.error}", color = MaterialTheme.colorScheme.error)
                }
                else -> {
                    // Afficher un message si on montre toutes les salles après une recherche vide
                    if (uiState.showAllAfterEmptySearch) {
                        Text(
                            "Aucune salle ne correspond à vos critères. Voici toutes les salles disponibles:",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(Modifier.height(8.dp))
                    }

                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(uiState.results) { space ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                                    .clickable {
                                        coroutineScope.launch {
                                            navController.navigate(dailyAvailabilityRoute(space.id))
                                        }
                                    }
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text(space.name, style = MaterialTheme.typography.titleMedium)
                                    Text(space.category)
                                    Text("Capacité : ${space.maxCapacity}")

                                    // Afficher les équipements disponibles
                                    Row {
                                        if (space.resources.any { it.contains("vidéoprojecteur", ignoreCase = true) }) {
                                            Text("📽️ ", modifier = Modifier.padding(end = 4.dp))
                                        }
                                        if (space.resources.any { it.contains("tableau blanc", ignoreCase = true) }) {
                                            Text("📝 ", modifier = Modifier.padding(end = 4.dp))
                                        }
                                        if (space.resources.any { it.contains("tele", ignoreCase = true) }) {
                                            Text("📺 ", modifier = Modifier.padding(end = 4.dp))
                                        }
                                        if (space.resources.any { it.contains("enceinte", ignoreCase = true) }) {
                                            Text("🔊 ", modifier = Modifier.padding(end = 4.dp))
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun FilterCheckbox(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = checked, onCheckedChange = onCheckedChange)
        Text(label)
    }
}
