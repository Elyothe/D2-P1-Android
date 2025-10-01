package com.example.d2_p1.admin.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.d2_p1.core.datasource.MockData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleryOneScreen() {
    val spaces = MockData.spaces

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        // Header avec bouton retour et titre
        TopAppBar(
            title = {
                Text(
                    text = "Espaces disponibles",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            },
            navigationIcon = {
                IconButton(onClick = { /* Navigation retour */ }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Retour"
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.White
            )
        )

        // Grille des espaces
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
                    hasWifi = space.resources.contains("wifi", ignoreCase = true)
                )
            }
        }
    }
}

@Composable
fun SpaceCard(
    spaceName: String,
    spaceType: String,
    capacity: Int,
    hasWifi: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Image de la salle (placeholder)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color(0xFFE0E0E0))
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "📋", // Emoji temporaire en attendant les vraies images
                    fontSize = 24.sp
                )
            }

            // Informations de la salle
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = spaceType,
                    fontSize = 12.sp,
                    color = Color.Gray
                )

                Text(
                    text = spaceName,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )

                // Icônes en bas
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Capacité",
                            modifier = Modifier.size(16.dp),
                            tint = Color(0xFFFF6B6B)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = capacity.toString(),
                            fontSize = 12.sp,
                            color = Color(0xFFFF6B6B)
                        )
                    }

                    if (hasWifi) {
                        Icon(
                            imageVector = Icons.Default.Call,
                            contentDescription = "WiFi disponible",
                            modifier = Modifier.size(16.dp),
                            tint = Color.Gray
                        )
                    }
                }
            }
        }
    }
}
