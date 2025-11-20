package com.nicolascommandeur.wikicat.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nicolascommandeur.wikicat.ui.viewmodels.BreedDetailsScreenViewModel

@Composable
fun BreedDetailsScreen(
    viewModel: BreedDetailsScreenViewModel,
    catBreedId: String?,
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val catBreed = uiState.catBreed

    // Fetch breed details from id when showing the Composable
    LaunchedEffect(Unit) {
        if (catBreed == null && catBreedId != null) {
            viewModel.fetchBreedInfoFromId(catBreedId)
        }
    }

    // Handle the system back button
    BackHandler(enabled = true) {
        onBackClick()
        viewModel.resetUiState()
    }

    // Show breed details if not in error
    catBreed?.let { breed ->
        Column {
            Text("Breed details screen : $catBreedId --> ${breed.name}")
            Button(onClick = {
                onBackClick()
                viewModel.resetUiState()
            }) {
                Text("Go back to home")
            }
        }
    }
}