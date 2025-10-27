package com.example.d2_p1.admin.ui.viewmodels

import androidx.collection.emptyLongSet
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.d2_p1.admin.domain.repositories.SpaceRepository
import com.example.d2_p1.admin.domain.events.EditSpaceEvent
import com.example.d2_p1.admin.domain.states.EditSpaceState
import com.example.d2_p1.core.data.models.Space
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.getValue

class EditSpaceViewModel : ViewModel(),KoinComponent {

    var state = androidx.compose.runtime.mutableStateOf(EditSpaceState())
    private val spaceRepository: SpaceRepository by inject()


    fun onEvent(event: EditSpaceEvent) {
        when (event) {
            is EditSpaceEvent.OnNameChanged -> state.value = state.value.copy(name = event.name)
            is EditSpaceEvent.OnCategoryChanged -> state.value = state.value.copy(category = event.category)
            is EditSpaceEvent.OnResourcesChanged -> state.value = state.value.copy(resources = event.ressources)
            is EditSpaceEvent.OnDescriptionChanged -> state.value = state.value.copy(description = event.description)
            is EditSpaceEvent.OnMaxCapacityChanged -> state.value = state.value.copy(maxCapacity = event.maxCapacity)
            is EditSpaceEvent.OnClickedSave -> editSpace()
            else -> Unit
        }

    }

    private fun editSpace() {
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

                spaceRepository.editSpace(newSpace)

                state.value = state.value.copy(isLoading = false, isSuccess = true)
            } catch (e: Exception) {
                state.value = state.value.copy(isLoading = false, errorMessage = e.message)
            }
        }
    }
}

