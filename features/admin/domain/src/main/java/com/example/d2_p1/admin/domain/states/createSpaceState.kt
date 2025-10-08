package com.example.d2_p1.admin.domain.states

data class CreateSpaceState(
    val name: String = "",
    val category: String = "",
    val maxCapacity: String = "",
    val description: String = "",
    val resources: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)
