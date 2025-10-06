package com.example.d2_p1.admin.ui.screens

import NavBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.d2_p1.core.ui.components.FormText
import com.example.d2_p1.core.ui.components.HeaderBarCustom

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
            HeaderBarCustom(
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

