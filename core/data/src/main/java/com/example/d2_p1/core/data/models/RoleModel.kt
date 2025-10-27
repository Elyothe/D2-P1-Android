package com.example.d2_p1.core.data.models
import kotlinx.serialization.Serializable


@Serializable
data class Role(
    val id: Int,
    val label: String,
    val code: Int
)