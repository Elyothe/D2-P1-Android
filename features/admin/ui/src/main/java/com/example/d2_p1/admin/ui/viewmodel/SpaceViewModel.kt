package com.example.d2_p1.admin.ui.viewmodel

import SpaceUiModel
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

class SpaceViewModel : ViewModel() {

    // Liste observable d'espaces
    val spaces: SnapshotStateList<SpaceUiModel> = mutableStateListOf(
        SpaceUiModel(
            id = "1",
            name = "Salle de réunion 1",
            description = "Grande salle avec écran",
            capacity = 12,
        ),
        SpaceUiModel(
            id = "2",
            name = "Bureau 42",
            description = "Petit bureau individuel",
            capacity = 1,
        )
    )

    // Ajout d'un espace depuis le formulaire
    fun addSpace(space: SpaceUiModel) {
        spaces.add(space.copy(id = (spaces.size + 1).toString(),))
    }
}
