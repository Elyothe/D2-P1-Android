package com.example.d2_p1.available.ui.screens

import NavBar
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.d2_p1.available.ui.viewmodels.AvailableSpacesViewModel
import com.example.d2_p1.core.data.models.Route.spaceDispoDetailsRoute
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AvailableSpacesScreen(
    navController: NavController,
    vm: AvailableSpacesViewModel = viewModel()
) {
    val uiState by vm.uiState.collectAsState()
    val context = LocalContext.current

    // États pour les dates et heures
    var startDate by remember { mutableStateOf(LocalDate.now()) }
    var startTime by remember { mutableStateOf(LocalTime.of(8, 0)) }
    var endDate by remember { mutableStateOf(LocalDate.now()) }
    var endTime by remember { mutableStateOf(LocalTime.of(10, 0)) }

    // États pour les dialogues
    var showStartDatePicker by remember { mutableStateOf(false) }
    var showStartTimePicker by remember { mutableStateOf(false) }
    var showEndDatePicker by remember { mutableStateOf(false) }
    var showEndTimePicker by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }

    // Formatters pour l'affichage
    val dateFormatter = remember { DateTimeFormatter.ofPattern("dd/MM/yyyy") }
    val timeFormatter = remember { DateTimeFormatter.ofPattern("HH:mm") }

    // Créer les calendriers pour les dialogues
    val startCalendar = Calendar.getInstance().apply {
        set(startDate.year, startDate.monthValue - 1, startDate.dayOfMonth)
    }
    val endCalendar = Calendar.getInstance().apply {
        set(endDate.year, endDate.monthValue - 1, endDate.dayOfMonth)
    }

    Scaffold(
        bottomBar = { NavBar(navController) },
        topBar = { TopAppBar(title = { Text("Espaces disponibles") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Sélecteur de date et heure de début
            OutlinedCard(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .clickable { showStartDatePicker = true }
                        .padding(16.dp)
                ) {
                    Text("Date et heure de début")
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("${startDate.format(dateFormatter)} ")
                        Text("${startTime.format(timeFormatter)}   ")
                        Icon(Icons.Default.DateRange, contentDescription = "Sélectionner")
                    }
                }
            }

            // Sélecteur de date et heure de fin
            OutlinedCard(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .clickable { showEndDatePicker = true }
                        .padding(16.dp)
                ) {
                    Text("Date et heure de fin")
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("${endDate.format(dateFormatter)} ")
                        Text("${endTime.format(timeFormatter)}   ")
                        Icon(Icons.Default.DateRange, contentDescription = "Sélectionner")
                    }
                }
            }

            Button(
                onClick = {
                    try {
                        val start = LocalDateTime.of(startDate, startTime)
                        val end = LocalDateTime.of(endDate, endTime)

                        if (end.isBefore(start)) {
                            showError = true
                            return@Button
                        }

                        vm.loadAvailableSpaces(start, end)
                        showError = false
                    } catch (e: Exception) {
                        showError = true
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Rechercher les espaces disponibles")
            }

            if (showError) {
                Text(
                    text = "La date de fin doit être postérieure à la date de début",
                    color = MaterialTheme.colorScheme.error
                )
            }

            when {
                uiState.isLoading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                uiState.error != null -> {
                    Text("Erreur: ${uiState.error}", color = MaterialTheme.colorScheme.error)
                }
                uiState.spaces.isEmpty() -> {
                    Text("Aucun espace disponible pour ce créneau.")
                }
                else -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(uiState.spaces) { space ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                                    .clickable {
                                        navController.navigate(spaceDispoDetailsRoute(space.id))
                                    }
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text(space.name, style = MaterialTheme.typography.titleMedium)
                                    Text(space.category)
                                    Text("Capacité: ${space.maxCapacity}")
                                    Text("Ressources: ${space.resources.joinToString(", ")}")
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // Dialogues pour la sélection des dates et heures
    if (showStartDatePicker) {
        DatePickerDialog(
            context,
            { _: DatePicker, year: Int, month: Int, day: Int ->
                startDate = LocalDate.of(year, month + 1, day)
                showStartDatePicker = false
                showStartTimePicker = true
            },
            startCalendar.get(Calendar.YEAR),
            startCalendar.get(Calendar.MONTH),
            startCalendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    if (showStartTimePicker) {
        TimePickerDialog(
            context,
            { _: TimePicker, hour: Int, minute: Int ->
                startTime = LocalTime.of(hour, minute)
                showStartTimePicker = false
            },
            startTime.hour,
            startTime.minute,
            true
        ).show()
    }

    if (showEndDatePicker) {
        DatePickerDialog(
            context,
            { _: DatePicker, year: Int, month: Int, day: Int ->
                endDate = LocalDate.of(year, month + 1, day)
                showEndDatePicker = false
                showEndTimePicker = true
            },
            endCalendar.get(Calendar.YEAR),
            endCalendar.get(Calendar.MONTH),
            endCalendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    if (showEndTimePicker) {
        TimePickerDialog(
            context,
            { _: TimePicker, hour: Int, minute: Int ->
                endTime = LocalTime.of(hour, minute)
                showEndTimePicker = false
            },
            endTime.hour,
            endTime.minute,
            true
        ).show()
    }
}
