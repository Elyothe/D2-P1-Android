package com.example.d2_p1.core.data.models
import kotlinx.serialization.Serializable

@Serializable
data class Reservation(
    val id: Int,
    val spaceId: Int,
    val userId: Int,
    val attendeesCount: Int,
    val startAt: String,
    val endAt: String,
    val status: StatusEnum,
    val space: Space? = null,
    val user: User? = null,
    val participants: List<ReservationParticipant> = emptyList()
)