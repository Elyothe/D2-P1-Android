package com.example.d2_p1.booking.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.d2_p1.booking.ui.viewmodels.BookingSpaceListViewModel

@Composable
fun BookingSpaceListScreen(
    viewModel: BookingSpaceListViewModel = viewModel()
) {
    val spaces by viewModel.spaces.collectAsState()

}