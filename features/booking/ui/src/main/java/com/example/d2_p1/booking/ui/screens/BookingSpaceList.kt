package com.example.d2_p1.booking.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.d2_p1.booking.ui.viewmodels.BookingSpaceListViewModel
import com.example.d2_p1.core.datasource.MockData

@Composable
fun BookingSpaceListScreen(
    viewModel: BookingSpaceListViewModel = viewModel()
) {
    val spaces = MockData.spaces

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        /*
        items(spaces) { space ->
            Text(space.name)
        }*/
    }
}
