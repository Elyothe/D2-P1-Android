package com.example.d2_p1.admin.ui.models
data class SpaceUiModel(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val photoUrl: String? = null,
    val capacity: Int = 0,
    val isActive: Boolean = true,
    val label: String = ""
)