package com.example.d2_p1.admin.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.d2_p1.admin.ui.models.SpaceUiModel
import com.example.d2_p1.core.data.models.Space
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.d2_p1.admin.domain.SpaceRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SpaceViewModel : ViewModel(),KoinComponent {

    val spaces: SnapshotStateList<SpaceUiModel> = mutableStateListOf()

    /**
     * Récupère les espaces depuis l’API et les mappe en UI model
     */
    init {
        loadSpaces()
    }
    fun loadSpaces() {

        val spaceRepository: SpaceRepository by inject()

        viewModelScope.launch{
            try {
                val apiSpaces = spaceRepository.getSpaces()
                spaces.clear()
                spaces.addAll(apiSpaces.map { it.toUiModel() })
            } catch (e: Exception) {
                Log.e("SpaceViewModel", "Erreur loadSpaces: ${e.message}", e)
            }
        }
    }

    /**
     * Crée un nouvel espace
     */


    /**
     * Met à jour un espace existant
     */


    /**
     * Supprime un espace
     */
    fun deleteSpace(id: Int) {
        viewModelScope.launch {
            try {
                val success = com.example.d2_p1.core.datasource.deleteSpace(id)
                if (success ) {
                    spaces.removeAll { it.id == id }
                }
            } catch (e: Exception) {
                Log.e("SpaceViewModel", "Erreur deleteSpace: ${e.message}", e)
            }
        }
    }
}

/**
 * Extensions de mapping API <-> UI
 */
fun Space.toUiModel() = SpaceUiModel(
    id = id,
    name = name,
    description = description,
    photoUrl = photoUrl,
    isActive = isActive,
    capacity = maxCapacity,
    label = category
)
