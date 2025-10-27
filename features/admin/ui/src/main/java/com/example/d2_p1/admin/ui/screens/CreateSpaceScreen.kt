package com.example.d2_p1.admin.ui.screens.create

import NavBar
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.app.ui.space.CreateSpaceViewModel
import com.example.d2_p1.core.ui.components.FormText
import com.example.d2_p1.core.ui.components.HeaderBarCustom
import com.example.d2_p1.admin.domain.events.CreateSpaceEvent

@Composable
fun CreateSpaceScreen(
    navController: NavController,
    viewModel: CreateSpaceViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
    )
) {
    val state by viewModel.state
    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current


    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            navController.popBackStack()
        }
    }

    Scaffold(
        bottomBar = { NavBar(navController) },
        modifier = Modifier.background(Color(0xFFF5F5F5)),
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        focusManager.clearFocus()
                    })
                }
                .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderBarCustom(
                title = "Créer une salle",
                showBackButton = true,
                onBackClick = { navController.popBackStack() }
            )

            FormText(
                title = "Nom de l’espace",
                contentLabel = "Entrez le nom",
                value = state.name, // 👈 récupérée du ViewModel
                onValueChange = { viewModel.onEvent(CreateSpaceEvent.OnNameChanged(it)) }
            )

            FormText(
                title = "Description",
                contentLabel = "Entrez la description",
                value = state.description,
                onValueChange = { viewModel.onEvent(CreateSpaceEvent.OnDescriptionChanged(it)) }
            )

            FormText(
                title = "Catégorie",
                contentLabel = "Ex: Salle de cours",
                value = state.category,
                onValueChange = { viewModel.onEvent(CreateSpaceEvent.OnCategoryChanged(it)) }
            )

            FormText(
                title = "Capacité maximale",
                contentLabel = "Ex: 25",
                value = state.maxCapacity,
                onValueChange = { viewModel.onEvent(CreateSpaceEvent.OnMaxCapacityChanged(it)) }
            )

            FormText(
                title = "Ressources (séparées par virgule)",
                contentLabel = "Ex: 1 TV, 2 tableaux",
                value = state.resources,
                onValueChange = { viewModel.onEvent(CreateSpaceEvent.OnResourcesChanged(it)) }
            )


            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { viewModel.onEvent(CreateSpaceEvent.OnClickedCreate) },
                enabled = !state.isLoading,
                modifier = Modifier.fillMaxWidth(0.9f)
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                } else {
                    Text("Créer la salle")
                }
            }

            // Message d’erreur
            state.errorMessage?.let {
                Text(text = it, color = Color.Red)
            }
        }
    }
}
