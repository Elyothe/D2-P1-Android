package com.example.d2_p1.admin.ui.viewmodels

import com.example.d2_p1.admin.domain.events.DetailSpaceEvent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.d2_p1.admin.domain.states.DetailSpaceState

import com.example.d2_p1.core.datasource.MockData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DetailSpaceViewModel : ViewModel() {

    var state = androidx.compose.runtime.mutableStateOf(DetailSpaceState())
        private set

    fun onEvent(event: DetailSpaceEvent) {
        when (event) {
            is DetailSpaceEvent.LoadSpace -> loadSpace(event.id)
            DetailSpaceEvent.OnBackClicked -> {
            }
        }
    }

    private fun loadSpace(id: Int) {
        viewModelScope.launch {
            state.value = state.value.copy(isLoading = true, errorMessage = null)

            delay(300)

            val space = MockData.spaces.find { it.id == id }

            if (space != null) {
                state.value = state.value.copy(space = space, isLoading = false)
            } else {
                state.value = state.value.copy(
                    isLoading = false,
                    errorMessage = "Espace introuvable"
                )
            }
        }
    }
}
