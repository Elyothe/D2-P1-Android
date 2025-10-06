package com.example.d2_p1.admin.data

import com.example.d2_p1.admin.domain.SpaceRepository
import com.example.d2_p1.core.datasource.MockData
import com.example.d2_p1.core.data.models.Space

class SpaceRepositoryImplementation : SpaceRepository {
     override suspend fun getSpaces(): List<Space> {
        return MockData.spaces
    }

    override suspend fun deleteSpace(id: Int): Boolean {
        return try {
            val spaceToRemove = MockData.spaces.find { it.id == id }
            if (spaceToRemove != null) {
                MockData.spaces.remove(spaceToRemove)
                true
            } else {
                false
            }
        } catch (e: Exception) {
            false
        }
    }
}