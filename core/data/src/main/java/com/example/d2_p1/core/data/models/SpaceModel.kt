package com.example.d2_p1.core.data.models

import kotlinx.serialization.Serializable


@Serializable
data class Space(
    val id: Int,
    val name: String,
    val description: String,
    val photoUrl: String?,
    val capacity: Int,
    val label : String,
    val isActive: Boolean,
    val reservations: List<Reservation> = emptyList() // liste de réservations
)


