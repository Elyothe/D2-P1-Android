package com.example.d2_p1.admin.domain

import com.example.d2_p1.core.data.models.Space

interface SpaceRepository {
    suspend fun getSpaces(): List<Space>
    suspend fun deleteSpace(id: Int): Boolean

    suspend fun createSpace(space: Space): Boolean
}