package com.example.d2_p1.available.ui.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.d2_p1.available.domain.SpaceRepository
import com.example.d2_p1.core.data.models.Space
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
data class SpaceDetailUiState(
    val isLoading: Boolean = false,
    val space: Space? = null,
    val error: String? = null
)

class SpaceDetailViewModel : ViewModel(), KoinComponent {
    private val repository: SpaceRepository by inject()
    private val _uiState = MutableStateFlow(SpaceDetailUiState())
    val uiState: StateFlow<SpaceDetailUiState> = _uiState

    fun loadSpaceDetails(spaceId: Int) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                val space = repository.getSpaces().find { it.id == spaceId }
                _uiState.value = _uiState.value.copy(space = space, isLoading = false)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message, isLoading = false)
            }
        }
    }
}