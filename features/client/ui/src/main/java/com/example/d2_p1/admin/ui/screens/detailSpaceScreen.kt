package com.example.d2_p1.admin.ui.screens

import DetailSpaceCard
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.d2_p1.admin.ui.viewmodels.DetailSpaceViewModel
import androidx.compose.material.icons.Icons

import NavBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.d2_p1.admin.domain.events.DetailSpaceEvent
import com.example.d2_p1.core.data.models.Route
import com.example.d2_p1.core.ui.components.FloatingButton
import com.example.d2_p1.core.ui.components.HeaderBarCustom

@Composable
fun DetailSpaceScreen(
    navController: NavController,
    spaceId: String,
    viewModel: DetailSpaceViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val state by viewModel.state
    val scrollState = rememberScrollState()

    LaunchedEffect(spaceId) {
        spaceId.toIntOrNull()?.let { id ->
            viewModel.onEvent(DetailSpaceEvent.LoadSpace(id))
        }
    }

    Scaffold(
        topBar = {
            Box(modifier = Modifier.padding(WindowInsets.statusBars.asPaddingValues())) {
                HeaderBarCustom(
                    title = state.space?.name ?: "Détails de la salle",
                    showBackButton = true,
                    onBackClick = { navController.popBackStack() }
                )
            }
        },
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
                    icon = Icons.Filled.Edit,
                    contentDescription = "Modifier un espace",
                    onClick = { navController.navigate("${Route.EditSpaceScreen}/${1}") }
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState),
            contentAlignment = Alignment.TopCenter
        ) {
            when {
                state.isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))

                state.errorMessage != null -> Text(
                    text = state.errorMessage!!,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Center)
                )

                else -> {
                    val space = state.space
                    if (space != null) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            DetailSpaceCard(space = space)
                        }
                    } else {
                        Text("Aucun espace trouvé", modifier = Modifier.align(Alignment.Center))
                    }
                }
            }
        }
    }
}




