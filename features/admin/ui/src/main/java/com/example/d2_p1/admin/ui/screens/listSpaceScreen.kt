package com.example.d2_p1.admin.ui.screens

import NavBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.d2_p1.admin.ui.components.SpaceCard
import com.example.d2_p1.core.data.models.Route
import com.example.d2_p1.core.ui.components.HeaderBarCustom
import com.example.d2_p1.core.ui.components.FloatingButton
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.d2_p1.admin.ui.viewmodels.SpaceViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListSpaceScreen(
    navController: NavController,
    viewModel: SpaceViewModel = viewModel(),
    loadOnStart: Boolean = true
) {
    val spaces = viewModel.spaces
    var showDeleteDialog by remember { mutableStateOf(false) }
    var spaceToDelete by remember { mutableStateOf<Int?>(null) }

    LaunchedEffect(loadOnStart) {
        if (loadOnStart) viewModel.loadSpaces()
    }

    // Boîte de dialogue de confirmation de suppression
    if (showDeleteDialog && spaceToDelete != null) {
        AlertDialog(
            onDismissRequest = {
                showDeleteDialog = false
                spaceToDelete = null
            },
            title = { Text("Confirmer la suppression") },
            text = {
                Text("Êtes-vous sûr de vouloir supprimer cette salle ? Cette action est irréversible.")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        spaceToDelete?.let { id ->
                            viewModel.deleteSpace(id)
                        }
                        showDeleteDialog = false
                        spaceToDelete = null
                    }
                ) {
                    Text("Supprimer", color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDeleteDialog = false
                        spaceToDelete = null
                    }
                ) {
                    Text("Annuler")
                }
            }
        )
    }

    Scaffold(
        bottomBar = { NavBar(navController) },
        modifier = Modifier.background(Color(0xFFF5F5F5)),
        floatingActionButton = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                FloatingButton(
                    icon = Icons.Filled.Add,
                    contentDescription = "Ajouter un espace",
                    onClick = { navController.navigate(Route.CreateSpaceScreen) }
                )
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            HeaderBarCustom(
                title = "Espaces Disponibles",
                showBackButton = true,
                onBackClick = { navController.popBackStack() }
            )

            if (spaces.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Aucune salle disponible",
                        color = Color.Gray
                    )
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(spaces) { space ->
                        SpaceCard(
                            spaceName = space.name,
                            spaceType = space.label,
                            capacity = space.capacity,
                            hasWifi = space.description.contains("wifi", ignoreCase = true),
                            onClick = {
                                navController.navigate("${Route.DetailSpaceScreen}/${space.id}")
                            },
                            onDeleteClick = {
                                spaceToDelete = space.id
                                showDeleteDialog = true
                            }
                        )
                    }
                }
            }
        }
    }
}



