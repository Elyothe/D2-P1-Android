package com.example.d2_p1.core.data.models
import kotlinx.serialization.Serializable


@Serializable
data class ReservationParticipant(
    val id: Int,
    val reservationId: Int,
    val userId: Int,
    val userName: String,
    val reservation: Reservation? = null, // référence à Reservation
    val user: User? = null                // référence à User
)