package com.example.d2_p1.admin.ui.screens

import NavBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.d2_p1.admin.ui.viewmodels.EditSpaceViewModel
import com.example.d2_p1.core.ui.components.HeaderBarCustom

@Composable
fun EditSpaceScreen(navController: NavController,
      spaceId: String,
      modifier: Modifier = Modifier,
      viewModel: EditSpaceViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
      Scaffold(
            topBar = {
                  Box(modifier = Modifier.padding(WindowInsets.statusBars.asPaddingValues())) {
                        HeaderBarCustom(
                              //title = state.space?.name ?: "Détails de la salle",
                              title = "Modifier une salle",
                              showBackButton = true,
                              onBackClick = { navController.popBackStack() }
                        )
                  }
            },
            bottomBar = { NavBar(navController) },
            modifier = Modifier.background(Color(0xFFF5F5F5)),
      ) { innerPadding ->
            Box(
                  modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
            ) {
            }
      }
}

