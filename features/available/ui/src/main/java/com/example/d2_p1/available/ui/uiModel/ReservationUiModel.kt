package com.example.d2_p1.available.ui.uiModel

data class ReservationUiModel(
    val id: Int,
    val startAt: String,
    val endAt: String,
    val attendeesCount: Int,
    val spaceId: Int
)