package com.example.d2_p1.admin.ui.screens

import NavBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.d2_p1.admin.ui.components.SpaceCard
import com.example.d2_p1.admin.ui.components.SpaceForm
import com.example.d2_p1.admin.ui.models.SpaceUiModel
import com.example.d2_p1.admin.ui.viewmodels.SpaceViewModel
import com.example.d2_p1.core.data.models.Route
import com.example.d2_p1.core.ui.components.FloatingButton
import com.example.d2_p1.core.ui.components.FormText
import com.example.d2_p1.core.ui.components.HeaderBar

@Composable
fun CreateSpaceScreen(
    navController: NavController,
) {
    Scaffold(
        bottomBar = { NavBar(navController) },
        modifier = Modifier.background(Color(0xFFF5F5F5)),
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(top = 16.dp), // marge en haut
            verticalArrangement = Arrangement.spacedBy(16.dp), // espace entre éléments
            horizontalAlignment = Alignment.CenterHorizontally // centrer horizontalement
        ) {
            HeaderBar(
                title = "Crée une salle",
                showBackButton = true,
                onBackClick = { navController.popBackStack() }
            )

            FormText(
                title = "Nom de l’espace",
                contentLabel = "Entrez le nom",
                onValueChange = { newValue ->
                    println("Texte saisi : $newValue")
                },
            )

            FormText(
                title = "Description de l’espace",
                contentLabel = "Entrez la description",
                onValueChange = { newValue ->
                    println("Texte saisi : $newValue")
                },
            )
        }

    }
}

