package com.example.d2_p1.available.data

import android.os.Build
import com.example.d2_p1.available.domain.AvailabilityRepository
import com.example.d2_p1.core.data.models.Reservation
import com.example.d2_p1.core.data.models.Space
import com.example.d2_p1.core.datasource.MockData
import java.time.LocalDate
import java.time.LocalDateTime

class AvailabilityRepositoryImpl : AvailabilityRepository {
    override suspend fun getDailyAvailability(spaceId: Int, date: LocalDate): List<Reservation> {
        return MockData.spaces
            .filter { it.id == spaceId }
            .flatMap { it.reservations }
            .filter { it.spaceId == spaceId && it.startAt.startsWith(date.toString()) }
    }

    override suspend fun getAvailableSpaces(start: LocalDateTime, end: LocalDateTime): List<Space> {
        return MockData.spaces.filter { space ->
            space.reservations.none { reservation ->
                val resStart = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    LocalDateTime.parse(reservation.startAt)
                } else {
                    TODO("VERSION.SDK_INT < O")
                }
                val resEnd = LocalDateTime.parse(reservation.endAt)
                !(end <= resStart || start >= resEnd)
            }
        }
    }

    override suspend fun getReservationsForSpace(spaceId: Int): List<Reservation> {
        return MockData.spaces.find { it.id == spaceId }?.reservations ?: emptyList()
    }

    override suspend fun getAllSpaces(): List<Space> {
        return MockData.spaces
    }

    override suspend fun searchSpaces(
        query: String,
        minCapacity: Int,
        hasProjector: Boolean,
        hasBoard: Boolean,
        hasTele: Boolean,
        hasEnceinte: Boolean
    ): List<Space> {
        return MockData.spaces.filter { space ->
            (query.isBlank() || space.name.contains(query, ignoreCase = true) ||
                    space.category.contains(query, ignoreCase = true)) &&
                    space.maxCapacity >= minCapacity &&
                    (!hasProjector || space.resources.any { it.contains("vidéoprojecteur", ignoreCase = true) }) &&
                    (!hasBoard || space.resources.any { it.contains("tableau blanc", ignoreCase = true) }) &&
                    (!hasTele || space.resources.any { it.contains("télévision", ignoreCase = true)  }) &&
                    (!hasEnceinte || space.resources.any { it.contains("enceinte", ignoreCase = true)})
        }
    }
}
