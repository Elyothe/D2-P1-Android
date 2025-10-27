package com.example.d2_p1.admin.data.repositoryImpl

import com.example.d2_p1.admin.domain.repositories.SpaceRepository
import com.example.d2_p1.core.data.models.Space
import com.example.d2_p1.core.datasource.MockData

class SpaceRepositoryImpl : SpaceRepository {

    override suspend fun getSpaces(): List<Space> {
        return MockData.spaces
    }

    override suspend fun createSpace(space: Space): Boolean {
        return try {
            val newId = (MockData.spaces.maxOfOrNull { it.id } ?: 0) + 1
            MockData.spaces.add(space.copy(id = newId))
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun editSpace(space: Space): Boolean {
        return try {
            val index = MockData.spaces.indexOfFirst { it.id == space.id }
            MockData.spaces[index] = space
            true
        }
        catch (e: Exception){
            e.printStackTrace()
            false
        }
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