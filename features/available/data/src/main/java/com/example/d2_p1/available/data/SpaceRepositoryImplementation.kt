package com.example.d2_p1.available.data

import com.example.d2_p1.available.domain.SpaceRepository
import com.example.d2_p1.core.datasource.MockData
import com.example.d2_p1.core.data.models.Space

class SpaceRepositoryImplementation : SpaceRepository {
     override suspend fun getSpaces(): List<Space> {
        return MockData.spaces
    }
}