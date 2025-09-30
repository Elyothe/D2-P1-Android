package com.example.d2_p1.admin.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.d2_p1.admin.ui.model.SpaceUiModel
import com.example.d2_p1.core.data.models.Space
import com.example.d2_p1.core.datasource.*
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

class SpaceViewModel : ViewModel() {

    val spaces: SnapshotStateList<SpaceUiModel> = mutableStateListOf()

    init {
        loadSpaces()
    }

    /**
     * Récupère les espaces depuis l’API et les mappe en UI model
     */
    fun loadSpaces() {
        viewModelScope.launch {
            try {
                val apiSpaces = getSpaces()
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
    fun addSpace(spaceUi: SpaceUiModel) {
        viewModelScope.launch {
            try {
                val created = createSpace(spaceUi.toApiModel())
                if (created != null) {
                    spaces.add(created.toUiModel())
                }
            } catch (e: Exception) {
                Log.e("SpaceViewModel", "Erreur addSpace: ${e.message}", e)
            }
        }
    }

    /**
     * Met à jour un espace existant
     */
    fun updateSpace(spaceUi: SpaceUiModel) {
        viewModelScope.launch {
            try {
                val success = updateSpace(spaceUi.toApiModel())
                if (success) {
                    val index = spaces.indexOfFirst { it.id == spaceUi.id }
                    if (index != -1) {
                        spaces[index] = spaceUi
                    }
                }
            } catch (e: Exception) {
                Log.e("SpaceViewModel", "Erreur updateSpace: ${e.message}", e)
            }
        }
    }

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
    capacity = capacity,
    isActive = isActive,
)

fun SpaceUiModel.toApiModel() = Space(
    id = id,
    name = name,
    description = description,
    photoUrl = photoUrl,
    capacity = capacity,
    isActive = isActive,
    label = label
)
