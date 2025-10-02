package com.example.d2_p1.admin.data

import com.example.d2_p1.admin.domain.spaceRepository
import com.example.d2_p1.core.datasource.MockData
import com.example.d2_p1.core.data.models.Space

class SpaceRepositoryImplementation : spaceRepository {
     override suspend fun getSpaces(): List<Space> {
        return MockData.spaces
    }
}