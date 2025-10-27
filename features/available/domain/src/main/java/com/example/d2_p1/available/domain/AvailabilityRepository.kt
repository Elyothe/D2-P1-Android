package com.example.d2_p1.available.domain

import com.example.d2_p1.core.data.models.Reservation
import com.example.d2_p1.core.data.models.Space
import java.time.LocalDate
import java.time.LocalDateTime

interface AvailabilityRepository {
    suspend fun getDailyAvailability(spaceId: Int, date: LocalDate): List<Reservation>
    suspend fun getAvailableSpaces(start: LocalDateTime, end: LocalDateTime): List<Space>
    suspend fun getReservationsForSpace(spaceId: Int): List<Reservation>
    suspend fun getAllSpaces(): List<Space>
    suspend fun searchSpaces(
        query: String,
        minCapacity: Int,
        hasProjector: Boolean,
        hasBoard: Boolean,
        hasTele: Boolean,
        hasEnceinte: Boolean
    ): List<Space>
}
