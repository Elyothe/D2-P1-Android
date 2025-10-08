package com.example.d2_p1.admin.domain.events

sealed class CreateSpaceEvent {
    data class OnNameChanged(val name: String) : CreateSpaceEvent()
    data class OnCategoryChanged(val category: String) : CreateSpaceEvent()
    data class OnMaxCapacityChanged(val capacity: String) : CreateSpaceEvent()
    data class OnDescriptionChanged(val description: String) : CreateSpaceEvent()
    data class OnResourcesChanged(val resources: String) : CreateSpaceEvent()
    object OnClickedCreate : CreateSpaceEvent()
}
