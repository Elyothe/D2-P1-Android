package com.example.d2_p1.available.ui.uiModel

data class SpaceUiModel(
    val id: Int,
    val name: String,
    val description: String,
    val photoUrl: String?,
    val isActive: Boolean,
    val capacity: Int,
    val label: String
)