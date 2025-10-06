package com.example.d2_p1.admin.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.d2_p1.admin.ui.components.SpaceForm
import com.example.d2_p1.admin.ui.models.SpaceUiModel

@Composable
fun ModifySpaceScreen(navController: NavController,
    modifier: Modifier = Modifier
) {
    val mockSpace = SpaceUiModel(
        id = 123,
        name = "Salle 24",
        description = "Ceci est une description",
        capacity = 10,
        label = "Projecteur"
    )
    SpaceForm(
        title = "Modify Space",
        spaceToEdit = mockSpace,
        onSaveClick = { println("Space modifié: $it") },
        modifier = modifier,
    )
}

