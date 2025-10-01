package com.example.d2_p1.admin.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.d2_p1.admin.ui.models.SpaceUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpaceForm(
    title: String,
    spaceToEdit: SpaceUiModel,
    onSaveClick: (SpaceUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    var nameState by remember(spaceToEdit.name) { mutableStateOf(spaceToEdit.name) }
    var descriptionState by remember(spaceToEdit.description) { mutableStateOf(spaceToEdit.description) }
    var capacityState by remember(spaceToEdit.capacity) { mutableStateOf(spaceToEdit.capacity.toString()) }
    var photoUrlState by remember(spaceToEdit.photoUrl) { mutableStateOf(spaceToEdit.photoUrl ?: "") }
    var isActiveState by remember(spaceToEdit.isActive) { mutableStateOf(spaceToEdit.isActive) }
    var labelState by remember(spaceToEdit.label) { mutableStateOf(spaceToEdit.label) }

    var expandedDropdown by remember { mutableStateOf(false) }
    val availableLabels = listOf("Projecteur", "Tableau", "Climatisation", "Aucun")

    LaunchedEffect(spaceToEdit.label) {
        if (!availableLabels.contains(labelState) && labelState.isNotEmpty()) {
            labelState = availableLabels.first() // ou "Aucun" ou ""
        } else if (labelState.isEmpty() && availableLabels.isNotEmpty()){
            labelState = availableLabels.first()
        }
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    //IconButton(onClick = onBackClick) {
                        //Icon(Icons.Default.ArrowBack, contentDescription = "Retour")
                    //}
                }
            )
        },
    ) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            OutlinedTextField(
                value = nameState,
                onValueChange = { nameState = it },
                label = { Text("Nom de l'espace") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = descriptionState,
                onValueChange = { descriptionState = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = capacityState,
                onValueChange = { newValue ->
                    if (newValue.all { it.isDigit() }) {
                        capacityState = newValue
                    }
                },
                label = { Text("Capacité maximale") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = photoUrlState,
                onValueChange = { photoUrlState = it },
                label = { Text("URL de la photo (optionnel)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))


            Text("Équipement principal:", style = MaterialTheme.typography.bodyLarge)
            ExposedDropdownMenuBox(
                expanded = expandedDropdown,
                onExpandedChange = { expandedDropdown = !expandedDropdown },
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = labelState,
                    onValueChange = {},
                    label = { Text("Label") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedDropdown) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    readOnly = true
                )
                ExposedDropdownMenu(
                    expanded = expandedDropdown,
                    onDismissRequest = { expandedDropdown = false }
                ) {
                    availableLabels.forEach { label ->
                        DropdownMenuItem(
                            text = { Text(label) },
                            onClick = {
                                labelState = label
                                expandedDropdown = false
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Actif:", style = MaterialTheme.typography.bodyLarge)
                Switch(
                    checked = isActiveState,
                    onCheckedChange = { isActiveState = it }
                )
            }
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val currentCapacity = capacityState.toIntOrNull() ?: 0
                    onSaveClick(
                        spaceToEdit.copy(
                            name = nameState,
                            description = descriptionState,
                            capacity = currentCapacity,
                            photoUrl = photoUrlState.ifBlank { null },
                            isActive = isActiveState,
                            label = labelState
                        )
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Enregistrer")
            }
        }
    }
}
