package com.nicolascommandeur.wikicat.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nicolascommandeur.wikicat.ui.viewmodels.HomeScreenViewModel

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val breedsList = uiState.breedsList

    Text("Breeds list length = ${breedsList.size}")
}