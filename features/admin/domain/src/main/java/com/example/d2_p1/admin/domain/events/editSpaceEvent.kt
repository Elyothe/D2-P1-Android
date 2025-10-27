package com.example.d2_p1.admin.domain.events

sealed class EditSpaceEvent {
    data class LoadSpace(val id: String) : EditSpaceEvent()
    data class OnNameChanged(val name: String) : EditSpaceEvent()
    data class OnDescriptionChanged(val description: String) : EditSpaceEvent()
    data class OnCategoryChanged(val category: String) : EditSpaceEvent()
    data class OnMaxCapacityChanged(val maxCapacity: String) : EditSpaceEvent()
    data class OnResourcesChanged(val ressources: String) : EditSpaceEvent()
    data object OnClickedSave : EditSpaceEvent()
}
