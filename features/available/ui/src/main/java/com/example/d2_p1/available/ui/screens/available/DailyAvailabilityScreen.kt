package com.example.d2_p1.available.ui.screens.available

import NavBar
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.d2_p1.available.ui.viewmodels.DailyAvailabilityViewModel
import com.example.d2_p1.available.ui.viewmodels.TimeSlot
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DailyAvailabilityScreen(
    navController: NavController,
    spaceId: Int,
    vm: DailyAvailabilityViewModel = viewModel()
) {
    val uiState by vm.uiState.collectAsState()
    var showDatePicker by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = selectedDate.toEpochDay() * 86400000
    )

    // Mettre à jour selectedDate lorsque la date est sélectionnée
    LaunchedEffect(datePickerState.selectedDateMillis) {
        datePickerState.selectedDateMillis?.let { millis ->
            selectedDate = LocalDate.ofEpochDay(millis / 86400000)
        }
    }

    // Charger les disponibilités lorsque la date ou l'ID de la salle change
    LaunchedEffect(spaceId, selectedDate) {
        vm.loadDailyAvailability(spaceId, selectedDate.toString())
    }

    val formattedDate = remember(selectedDate) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            try {
                selectedDate.format(DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy", java.util.Locale.getDefault()))
            } catch (e: Exception) {
                selectedDate.toString()
            }
        } else {
            // Solution de repli pour les versions antérieures
            val parts = selectedDate.toString().split("-")
            "${parts[2]}/${parts[1]}/${parts[0]}"
        }
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDatePicker = false
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDatePicker = false
                    }
                ) {
                    Text("Annuler")
                }
            }
        ) {
            DatePicker(
                state = datePickerState
            )
        }
    }

    Scaffold(
        bottomBar = { NavBar(navController) },
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Disponibilités pour la salle #$spaceId") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Retour")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Disponibilités",
                    style = MaterialTheme.typography.headlineSmall
                )
            }

            // Champ de texte pour la date
            OutlinedTextField(
                value = formattedDate,
                onValueChange = {},
                readOnly = true,
                label = { Text("Date") },
                trailingIcon = {
                    IconButton(onClick = { showDatePicker = true }) {
                        Icon(Icons.Default.DateRange , contentDescription = "Sélectionner une date")
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { selectedDate = LocalDate.now() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                ) {
                    Text("Aujourd'hui")
                }
            }

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
                uiState.availableSlots.isEmpty() -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Aucun créneau disponible pour cette date.")
                    }
                }
                else -> {
                    Text(
                        "Créneaux horaires:",
                        style = MaterialTheme.typography.titleMedium
                    )
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(uiState.availableSlots) { slot ->
                            val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = if (slot.isAvailable) {
                                        MaterialTheme.colorScheme.surfaceContainer
                                    } else {
                                        MaterialTheme.colorScheme.errorContainer
                                    }
                                )
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                            Text(
                                                text = "${slot.startTime.format(timeFormatter)} - ${slot.endTime.format(timeFormatter)}",
                                                style = MaterialTheme.typography.bodyLarge
                                            )
                                        } else {
                                            Text(
                                                text = "${slot.startTime} - ${slot.endTime}",
                                                style = MaterialTheme.typography.bodyLarge
                                            )
                                        }
                                        Text(
                                            text = if (slot.isAvailable) "Disponible" else "Occupé",
                                            color = if (slot.isAvailable) {
                                                MaterialTheme.colorScheme.onSurface
                                            } else {
                                                MaterialTheme.colorScheme.error
                                            },
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                    }
                                    if (slot.isAvailable) {
                                        Button(
                                            onClick = { /* Action pour réserver ce créneau */ },
                                            modifier = Modifier.align(Alignment.End)
                                        ) {
                                            Text("Réserver")
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
