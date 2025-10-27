package com.example.d2_p1.available.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.d2_p1.available.domain.AvailabilityRepository
import com.example.d2_p1.core.data.models.Space
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.time.LocalDateTime

data class AvailableSpacesUiState(
    val isLoading: Boolean = false,
    val spaces: List<Space> = emptyList(),
    val error: String? = null
)

class AvailableSpacesViewModel : ViewModel(), KoinComponent {
    private val repository: AvailabilityRepository by inject()
    private val _uiState = MutableStateFlow(AvailableSpacesUiState())
    val uiState: StateFlow<AvailableSpacesUiState> = _uiState

    fun loadAvailableSpaces(start: LocalDateTime, end: LocalDateTime) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                val spaces = repository.getAvailableSpaces(start, end)
                _uiState.value = _uiState.value.copy(spaces = spaces, isLoading = false)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message, isLoading = false)
            }
        }
    }
}