package com.example.d2_p1.admin.ui.screens

import NavBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.d2_p1.admin.ui.components.SpaceCard
import com.example.d2_p1.core.datasource.MockData
import com.example.d2_p1.core.ui.components.HeaderBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleryOneScreen(navController: NavController) {
    val spaces = MockData.spaces

    Scaffold(
        bottomBar = { NavBar(navController) },
        modifier = Modifier.background(Color(0xFFF5F5F5))
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            HeaderBar(
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
                        hasWifi = space.resources.contains("wifi", ignoreCase = true),
                        onClick = {
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}

