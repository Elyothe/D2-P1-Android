package com.example.d2_p1.admin.domain

import com.example.d2_p1.core.data.models.Space

interface spaceRepository {
    suspend fun getSpaces(): List<Space>
}