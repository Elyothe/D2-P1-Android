package com.example.d2_p1.available.ui.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.d2_p1.available.domain.AvailabilityRepository
import com.example.d2_p1.core.data.models.Reservation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

data class ReservationsUiState(
    val isLoading: Boolean = false,
    val reservations: List<Reservation> = emptyList(),
    val error: String? = null
)

class ReservationsViewModel : ViewModel(), KoinComponent {
    private val repository: AvailabilityRepository by inject()
    private val _uiState = MutableStateFlow(ReservationsUiState())
    val uiState: StateFlow<ReservationsUiState> = _uiState

    fun loadReservations(spaceId: Int) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                val reservations = repository.getReservationsForSpace(spaceId)
                _uiState.value = _uiState.value.copy(reservations = reservations, isLoading = false)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message, isLoading = false)
            }
        }
    }
}
