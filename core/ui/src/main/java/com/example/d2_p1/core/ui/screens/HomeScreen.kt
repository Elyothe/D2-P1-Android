package com.example.d2_p1.core.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import component.CustomNavigationBar

@Composable
fun HomeScreen(
    onNavigate: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Contenu principal
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Bienvenue sur la Home")
        }

        // Barre de navigation en bas
        CustomNavigationBar(onNavigate = onNavigate)
    }
}
