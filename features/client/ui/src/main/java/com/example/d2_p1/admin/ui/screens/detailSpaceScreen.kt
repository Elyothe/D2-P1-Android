package com.example.d2_p1.admin.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.d2_p1.admin.ui.viewmodels.DetailSpaceViewModel

import NavBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.d2_p1.admin.domain.events.DetailSpaceEvent
import com.example.d2_p1.core.ui.components.HeaderBarCustom

@Composable
fun DetailSpaceScreen(
    navController: NavController,
    spaceId: String,
    viewModel: DetailSpaceViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val state by viewModel.state

    LaunchedEffect(spaceId) {
        spaceId.toIntOrNull()?.let { id ->
            viewModel.onEvent(DetailSpaceEvent.LoadSpace(id))
        }
    }

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .padding(WindowInsets.statusBars.asPaddingValues())
            ) {
                HeaderBarCustom(
                    title = state.space?.name ?: "Détails de la salle",
                    showBackButton = true,
                    onBackClick = { navController.popBackStack() }
                )
            }
        },
        bottomBar = { NavBar(navController) },
        modifier = Modifier.background(Color(0xFFF5F5F5))
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                state.errorMessage != null -> {
                    Text(
                        text = state.errorMessage!!,
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                else -> {
                    val space = state.space
                    if (space != null) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .align(Alignment.TopCenter),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text("Catégorie : ${space.category}")
                            Text("Capacité : ${space.maxCapacity}")
                            Text("Description : ${space.description}")

                            Spacer(modifier = Modifier.height(12.dp))

                            Text("Ressources :", style = MaterialTheme.typography.titleMedium)
                            space.resources.forEach {
                                Text("- $it")
                            }
                        }
                    } else {
                        Text(
                            "Aucun espace trouvé",
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}



