package com.example.d2_p1.core.data.models

import kotlinx.serialization.Serializable


@Serializable
data class Space(
    val id: Int,
    val name: String,
    val category: String,
    val maxCapacity: Int,
    val resources: String,
    val description: String,
    val photoUrl: String? = null,
    val isActive: Boolean = true,
    val reservations: List<Reservation> = emptyList()
)
