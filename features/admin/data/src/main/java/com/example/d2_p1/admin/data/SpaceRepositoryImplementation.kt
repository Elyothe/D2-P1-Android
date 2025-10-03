package com.example.d2_p1.admin.data

import com.example.d2_p1.admin.domain.SpaceRepository
import com.example.d2_p1.core.datasource.MockData
import com.example.d2_p1.core.data.models.Space

class SpaceRepositoryImplementation : SpaceRepository {
     override suspend fun getSpaces(): List<Space> {
        return MockData.spaces
    }

    override suspend fun deletSpace(id: Int): Boolean {
        try {
            val spaceExists = MockData.spaces.any { it.id == id }
            return spaceExists
        } catch (e: Exception) {
            return false
        }
    }
}