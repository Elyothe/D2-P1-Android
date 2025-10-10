package com.example.d2_p1.available.ui.uiModel
import com.example.d2_p1.available.ui.uiModel.ReservationUiModel
import com.example.d2_p1.core.data.models.Reservation


fun Reservation.toUiModel() = ReservationUiModel(
    id = id,
    startAt = startAt,
    endAt = endAt,
    attendeesCount = attendeesCount,
    spaceId = spaceId
)