package com.example.d2_p1.available.ui.viewmodels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.d2_p1.available.domain.AvailabilityRepository
import com.example.d2_p1.core.data.models.Reservation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

data class TimeSlot(
    val startTime: LocalTime,
    val endTime: LocalTime,
    var isAvailable: Boolean = true
)

data class DailyAvailabilityUiState(
    val isLoading: Boolean = false,
    val availableSlots: List<TimeSlot> = emptyList(),
    val error: String? = null,
    val spaceId: Int = 0,
    val dateString: String = ""
)

class DailyAvailabilityViewModel : ViewModel(), KoinComponent {
    private val repository: AvailabilityRepository by inject()
    private val _uiState = MutableStateFlow(DailyAvailabilityUiState())
    val uiState: StateFlow<DailyAvailabilityUiState> = _uiState

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadDailyAvailability(spaceId: Int, dateString: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                error = null,
                spaceId = spaceId,
                dateString = dateString
            )
            try {
                val reservations = repository.getDailyAvailability(spaceId, parseDate(dateString))
                val availableSlots = generateTimeSlots(reservations, parseDate(dateString))
                _uiState.value = _uiState.value.copy(
                    availableSlots = availableSlots,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = "Erreur lors du chargement des disponibilités: ${e.message}",
                    isLoading = false
                )
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun generateTimeSlots(reservations: List<Reservation>, date: LocalDate): List<TimeSlot> {
        val openingTime = LocalTime.of(8, 0)
        val closingTime = LocalTime.of(20, 0)
        val slotDuration = java.time.Duration.ofHours(1)
        val allSlots = mutableListOf<TimeSlot>()
        var currentTime = openingTime
        while (currentTime.isBefore(closingTime)) {
            val endTime = currentTime.plus(slotDuration)
            allSlots.add(TimeSlot(currentTime, endTime))
            currentTime = endTime
        }
        val slotsWithAvailability = allSlots.map { it.copy() }.toMutableList()
        val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
        reservations.forEach { reservation ->
            try {
                val start = LocalTime.parse(reservation.startAt.split("T")[1])
                val end = LocalTime.parse(reservation.endAt.split("T")[1])
                slotsWithAvailability.forEach { slot ->
                    if (!(end.isBefore(slot.startTime) || start.isAfter(slot.endTime))) {
                        slot.isAvailable = false
                    }
                }
            } catch (e: Exception) {
                println("Erreur de parsing: ${e.message}")
            }
        }
        return slotsWithAvailability
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun parseDate(dateString: String): LocalDate {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDate.parse(dateString)
        } else {
            // Solution de repli pour les versions antérieures
            val parts = dateString.split("-")
            LocalDate.of(parts[0].toInt(), parts[1].toInt(), parts[2].toInt())
        }
    }
}
