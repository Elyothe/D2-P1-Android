package com.example.d2_p1.available.ui.uiModel

import com.example.d2_p1.core.data.models.Space

fun Space.toUiModel() = SpaceUiModel(
    id = id,
    name = name,
    description = description,
    photoUrl = photoUrl,
    isActive = isActive,
    capacity = maxCapacity,
    label = category
)