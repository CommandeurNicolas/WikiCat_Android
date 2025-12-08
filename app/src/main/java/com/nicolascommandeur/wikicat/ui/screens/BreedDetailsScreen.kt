package com.nicolascommandeur.wikicat.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nicolascommandeur.wikicat.ui.viewmodels.BreedDetailsScreenUiState
import com.nicolascommandeur.wikicat.ui.viewmodels.BreedDetailsScreenViewModel

@Composable
fun BreedDetailsScreen(
    viewModel: BreedDetailsScreenViewModel,
    catBreedId: String?,
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // Fetch breed details from id when showing the Composable
    LaunchedEffect(Unit) {
        if (uiState is BreedDetailsScreenUiState.Loading && catBreedId != null) {
            viewModel.fetchBreedInfoFromId(catBreedId)
        }
    }

    // Handle the system back button
    BackHandler(enabled = true) {
        onBackClick()
        viewModel.resetUiState()
    }

    Column {
        when(uiState) {
            BreedDetailsScreenUiState.Loading -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }
            is BreedDetailsScreenUiState.Success -> {
                // TODO: add proper details view
                val breed = (uiState as BreedDetailsScreenUiState.Success).catBreed
                if (breed != null) {
                    Text("Breed name : ${breed.name}")
                } else {
                    Text("No breed found")
                }
            }
            is BreedDetailsScreenUiState.Error -> {
                // TODO: add proper error view
                Text("An error occurred")
            }
        }

        Button(onClick = {
            onBackClick()
            viewModel.resetUiState()
        }) {
            Text("Go back to home")
        }
    }
}