package com.example.d2_p1.core.data.models

import kotlinx.serialization.Serializable


@Serializable
data class User(
    val id: Int,
    val mail: String,
    val displayName: String,
    val isActive: Boolean,
    val roleId: Int,
    val role: Role? = null,
    val reservations: List<Reservation> = emptyList()
)