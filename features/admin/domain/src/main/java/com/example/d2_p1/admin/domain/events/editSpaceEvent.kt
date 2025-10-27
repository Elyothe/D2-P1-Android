package com.example.d2_p1.admin.domain.events

sealed class EditSpaceEvent {
    data class LoadSpace(val id: String) : EditSpaceEvent()
    data class OnNameChanged(val value: String) : EditSpaceEvent()
    data class OnDescriptionChanged(val value: String) : EditSpaceEvent()
    data class OnCategoryChanged(val value: String) : EditSpaceEvent()
    data class OnMaxCapacityChanged(val value: String) : EditSpaceEvent()
    data class OnResourcesChanged(val value: String) : EditSpaceEvent()
    data object OnClickedSave : EditSpaceEvent()
}
