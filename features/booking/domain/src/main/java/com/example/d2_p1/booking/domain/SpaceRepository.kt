package com.example.d2_p1.booking.domain

import com.example.d2_p1.core.data.models.Space

interface SpaceRepository {
    suspend fun getSpaces(): List<Space>
}