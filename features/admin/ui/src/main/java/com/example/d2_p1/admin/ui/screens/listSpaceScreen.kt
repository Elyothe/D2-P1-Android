package com.example.d2_p1.admin.ui.screens

import NavBar
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.d2_p1.admin.ui.components.SpaceCard
import com.example.d2_p1.core.data.models.Route
import com.example.d2_p1.core.ui.components.HeaderBar
import com.example.d2_p1.core.datasource.MockData
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

    // Charger les espaces à l'entrée de l'écran seulement si demandé
    LaunchedEffect(loadOnStart) {
        if (loadOnStart) {
            viewModel.loadSpaces()
        }
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
                    contentDescription = "Modifier",
                    onClick = { navController.navigate(Route.CreateSpaceScreen) }
                )
            }
        },
        // Pas de snackbar pour le moment, on se concentre sur l'affichage
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
                        spaceType = space.category,
                        capacity = space.maxCapacity,
                        hasWifi = space.resources.contains("wifi"),
                        onClick = {
                            navController.popBackStack()
                        }
                    )
            if (spaces.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Aucune salle disponible", color = Color.Gray)
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
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun ListSpaceScreenPreview() {
    val navController = rememberNavController()
    val previewViewModel = SpaceViewModel()
    LaunchedEffect(Unit) {
        previewViewModel.loadSpaces()
    }
    ListSpaceScreen(
        navController = navController,
        viewModel = previewViewModel,
        loadOnStart = true
    )
}
