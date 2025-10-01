package com.example.d2_p1.core.data.models

import kotlinx.serialization.Serializable


@Serializable
data class Space(
    val id: Int,
    val name: String, // Nom de la salle
    val category: String, // Catégorie de salle
    val maxCapacity: Int, // Capacité (corrigé la faute de frappe)
    val resources: String, // Ressources
    val description: String, // Description
    val photoUrl: String? = null,
    val isActive: Boolean = true,
    val reservations: List<Reservation> = emptyList() // liste de réservations
)
