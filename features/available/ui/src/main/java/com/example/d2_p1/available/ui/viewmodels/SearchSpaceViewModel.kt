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


data class SearchSpaceUiState(
    val query: String = "",
    val capacity: Float = 5f,
    val projector: Boolean = false,
    val tele: Boolean = false,
    val enceinte: Boolean = false,
    val board: Boolean = false,
    val isLoading: Boolean = false,
    val results: List<Space> = emptyList(),
    val error: String? = null,
    val showToast: Boolean = false,
    val toastMessage: String = "",
    val showAllAfterEmptySearch: Boolean = false
)

class SearchSpaceViewModel : ViewModel(), KoinComponent {
    private val repository: AvailabilityRepository by inject()
    private val _uiState = MutableStateFlow(SearchSpaceUiState())
    val uiState: StateFlow<SearchSpaceUiState> = _uiState

    init {
        loadAllSpaces()
    }

    fun onQueryChange(value: String) {
        _uiState.value = _uiState.value.copy(query = value)
    }

    fun onCapacityChange(value: Float) {
        _uiState.value = _uiState.value.copy(capacity = value)
    }

    fun onProjectorChange(value: Boolean) {
        _uiState.value = _uiState.value.copy(projector = value)
    }

    fun onBoardChange(value: Boolean) {
        _uiState.value = _uiState.value.copy(board = value)
    }

    fun onTeleChange(value: Boolean) {
        _uiState.value = _uiState.value.copy(tele = value)
    }

    fun onEnceinteChange(value: Boolean) {
        _uiState.value = _uiState.value.copy(enceinte = value)
    }

    private fun loadAllSpaces() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val allSpaces = repository.getAllSpaces()
                _uiState.value = _uiState.value.copy(
                    results = allSpaces,
                    isLoading = false,
                    showAllAfterEmptySearch = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message,
                    isLoading = false
                )
            }
        }
    }

    fun search() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)

            try {
                val current = _uiState.value

                // Vérifier si des filtres sont actifs
                val hasActiveFilters = current.query.isNotBlank() ||
                        current.capacity > 1f ||
                        current.projector ||
                        current.board ||
                        current.tele ||
                        current.enceinte

                // Effectuer la recherche avec les filtres
                val filteredResults = repository.searchSpaces(
                    query = current.query,
                    minCapacity = current.capacity.toInt(),
                    hasProjector = current.projector,
                    hasBoard = current.board,
                    hasTele = current.tele,
                    hasEnceinte = current.enceinte
                )

                if (hasActiveFilters && filteredResults.isEmpty()) {
                    // Si des filtres sont actifs mais aucun résultat, charger toutes les salles
                    val allSpaces = repository.getAllSpaces()
                    _uiState.value = _uiState.value.copy(
                        results = allSpaces,
                        isLoading = false,
                        showToast = true,
                        toastMessage = "Aucune salle ne correspond à vos critères. Voici toutes les salles disponibles.",
                        showAllAfterEmptySearch = true
                    )
                } else {
                    // Sinon, afficher les résultats filtrés (ou toutes les salles si aucun filtre)
                    _uiState.value = _uiState.value.copy(
                        results = if (hasActiveFilters) filteredResults else repository.getAllSpaces(),
                        isLoading = false,
                        showAllAfterEmptySearch = !hasActiveFilters
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message,
                    isLoading = false
                )
            }
        }
    }

    fun resetToast() {
        _uiState.value = _uiState.value.copy(showToast = false)
    }
}