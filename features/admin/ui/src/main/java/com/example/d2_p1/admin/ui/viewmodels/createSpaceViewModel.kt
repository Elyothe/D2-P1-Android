package com.example.app.ui.space

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import com.example.d2_p1.admin.domain.SpaceRepository
import com.example.d2_p1.admin.domain.events.CreateSpaceEvent
import com.example.d2_p1.admin.domain.states.CreateSpaceState
import com.example.d2_p1.core.data.models.Space
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.getValue


class CreateSpaceViewModel: ViewModel(), KoinComponent {

    private val spaceRepository: SpaceRepository by inject()
    var state = mutableStateOf(CreateSpaceState())
        private set

    fun onEvent(event: CreateSpaceEvent) {
        when (event) {
            is CreateSpaceEvent.OnNameChanged -> state.value = state.value.copy(name = event.name)
            is CreateSpaceEvent.OnCategoryChanged -> state.value = state.value.copy(category = event.category)
            is CreateSpaceEvent.OnMaxCapacityChanged -> state.value = state.value.copy(maxCapacity = event.capacity)
            is CreateSpaceEvent.OnDescriptionChanged -> state.value = state.value.copy(description = event.description)
            is CreateSpaceEvent.OnResourcesChanged -> state.value = state.value.copy(resources = event.resources)
            is CreateSpaceEvent.OnClickedCreate -> createSpace()
        }
    }

    private fun createSpace() {
        viewModelScope.launch {
            state.value = state.value.copy(isLoading = true, errorMessage = null)

            try {
                val newSpace = Space(
                    id = 0,
                    name = state.value.name,
                    category = state.value.category,
                    maxCapacity = state.value.maxCapacity.toIntOrNull() ?: 0,
                    resources = state.value.resources.split(",").map { it.trim() },
                    description = state.value.description,
                    photoUrl = null,
                    isActive = true,
                    reservations = emptyList()
                )

                spaceRepository.createSpace(newSpace)

                state.value = state.value.copy(isLoading = false, isSuccess = true)
            } catch (e: Exception) {
                state.value = state.value.copy(isLoading = false, errorMessage = e.message)
            }
        }
    }
}

