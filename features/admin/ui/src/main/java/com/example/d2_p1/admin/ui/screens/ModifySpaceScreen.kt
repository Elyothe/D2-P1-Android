package com.example.d2_p1.admin.ui.screens

import SpaceUiModel
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.d2_p1.admin.ui.component.SpaceForm

@Composable
fun ModifySpaceScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Données en dur pour le preview
    val mockSpace = SpaceUiModel(
        id = "123",
        name = "Salle 24",
        description = "Ceci est une description",
        capacity = 10,
        label = "Projecteur"
    )
    SpaceForm(
        title = "Modify Space",
        initialState = mockSpace,
        onBackClick = onBackClick,
        onSaveClick = { updatedSpace -> println("Espace modifié: $updatedSpace") },
        modifier = modifier
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewModifySpaceScreen() {
    ModifySpaceScreen(
        onBackClick = {}
    )
}
