package com.example.d2_p1.admin.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.d2_p1.admin.ui.models.SpaceUiModel
import com.example.d2_p1.core.data.models.Space
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.d2_p1.admin.domain.repositories.SpaceRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SpaceViewModel : ViewModel(),KoinComponent {

    private val spaceRepository: SpaceRepository by inject()
    val spaces: SnapshotStateList<SpaceUiModel> = mutableStateListOf()

    /**
     * Récupère les espaces depuis l'API et les mappe en UI model
     */
    init {
        loadSpaces()
    }

    fun loadSpaces() {
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
     * Supprime un espace
     */
    fun deleteSpace(id: Int) {
        viewModelScope.launch {
            try {
                val success = spaceRepository.deleteSpace(id)
                if (success) {
                    spaces.removeAll { it.id == id }
                    Log.d("SpaceViewModel", "Espace supprimé avec succès: $id")
                } else {
                    Log.w("SpaceViewModel", "Échec de la suppression de l'espace: $id")
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
