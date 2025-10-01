package com.example.d2_p1.admin.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.d2_p1.admin.ui.component.SpaceForm
import com.example.d2_p1.admin.ui.model.SpaceUiModel
import com.example.d2_p1.admin.ui.viewmodel.SpaceViewModel

@Composable
fun CreateSpaceScreen(
    spaceViewModel: SpaceViewModel = viewModel(),
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    SpaceForm(
        title = "Create Space",
        spaceToEdit = SpaceUiModel(),
        onBackClick = onBackClick,
        onSaveClick = { spaceUi ->
            // Appelle le ViewModel pour créer l'espace via l'API
            spaceViewModel.addSpace(spaceUi)
            // Optionnel : revenir à l'écran précédent après création
            onBackClick()
        },
        modifier = modifier,
        isCreating = true
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCreateSpaceScreen() {
    val dummyViewModel = SpaceViewModel()
    CreateSpaceScreen(
        spaceViewModel = dummyViewModel,
        onBackClick = {}
    )
}
