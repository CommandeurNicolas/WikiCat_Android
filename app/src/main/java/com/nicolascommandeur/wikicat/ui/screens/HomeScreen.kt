package com.nicolascommandeur.wikicat.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nicolascommandeur.wikicat.ui.viewmodels.HomeScreenViewModel

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val breedsList = uiState.breedsList
    val isInErrorState = uiState.error

    if (isInErrorState) {
        // TODO: add proper error view
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text("An error occurred, check your connection and restart the app")
        }
    }
    else if (breedsList.isEmpty()) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    }
    else {
        Text("Breeds list length = ${breedsList.size}")
    }
}