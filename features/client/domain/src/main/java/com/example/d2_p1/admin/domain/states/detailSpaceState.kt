package com.example.d2_p1.admin.domain.states

import com.example.d2_p1.core.data.models.Space


data class DetailSpaceState(
    val space: Space? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)