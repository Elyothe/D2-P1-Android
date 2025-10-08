package com.example.d2_p1.booking.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.d2_p1.booking.domain.SpaceRepository
import com.example.d2_p1.core.data.models.Space
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BookingSpaceListViewModel : ViewModel(), KoinComponent {
    private val repository: SpaceRepository by inject()

    private val _spaces = MutableStateFlow<List<Space>>(emptyList())
    val spaces: StateFlow<List<Space>> = _spaces

    init {
        loadSpaces()
    }

    private fun loadSpaces() {
        viewModelScope.launch {
            _spaces.value = repository.getSpaces()
        }
    }
}