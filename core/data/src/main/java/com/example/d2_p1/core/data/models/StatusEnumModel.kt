package com.example.d2_p1.core.data.models

import kotlinx.serialization.Serializable


@Serializable
enum class StatusEnum {
    PENDING,
    CONFIRMED,
    CANCELLED
}
