package com.example.d2_p1.admin.ui.screens

import SpaceUiModel
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.d2_p1.admin.ui.component.SpaceForm

@Composable
fun CreateSpaceScreen(
    onAddSpace: (SpaceUiModel) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    SpaceForm(
        title = "Create Space",
        initialState = SpaceUiModel(),
        onBackClick = onBackClick,
        onSaveClick = onAddSpace,
        modifier = modifier
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCreateSpaceScreen() {
    CreateSpaceScreen(
        onAddSpace = { println("Nouvel espace: $it") },
        onBackClick = {}
    )
}
