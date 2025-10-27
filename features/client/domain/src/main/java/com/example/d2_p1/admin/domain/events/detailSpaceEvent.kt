package com.example.d2_p1.admin.domain.events


sealed class DetailSpaceEvent {
    data class LoadSpace(val id: Int) : DetailSpaceEvent()
    object OnBackClicked : DetailSpaceEvent()
}
