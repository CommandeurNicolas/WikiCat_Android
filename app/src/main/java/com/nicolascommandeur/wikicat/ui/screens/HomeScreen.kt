package com.nicolascommandeur.wikicat.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nicolascommandeur.wikicat.domain.models.CatBreed
import com.nicolascommandeur.wikicat.ui.components.HomeBreedCardComposable
import com.nicolascommandeur.wikicat.ui.viewmodels.HomeScreenUiState
import com.nicolascommandeur.wikicat.ui.viewmodels.HomeScreenViewModel

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel,
    onNavigateToDetails: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when(val state = uiState) {
        HomeScreenUiState.Loading -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }
        is HomeScreenUiState.Empty -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column {
                    Text("No cat found, the list is empty")
                }
            }
        }
        is HomeScreenUiState.Success -> HomeScreenListView(state.breedsList, onNavigateToDetails)
        is HomeScreenUiState.Error -> {
            // TODO: add proper error view
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column {
                    Text("An error occurred, check your connection and restart the app")
                    Text(state.errorMessage)
                }
            }
        }
    }
}

@Composable
private fun HomeScreenListView(
    breedsList: List<CatBreed>,
    onNavigateToDetails: (String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(128.dp),
        contentPadding = PaddingValues(
            start = 8.dp,
            end = 8.dp,
            top = 12.dp,
            bottom = 12.dp
        )
    ) {
        items(items = breedsList) { breed ->
            HomeBreedCardComposable(
                breed = breed,
                modifier = Modifier
                    .clickable(enabled = true, onClick = { onNavigateToDetails(breed.id) })
            )
        }
    }
}