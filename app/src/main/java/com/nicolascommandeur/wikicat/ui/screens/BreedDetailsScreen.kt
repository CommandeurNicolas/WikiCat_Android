package com.nicolascommandeur.wikicat.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BreedDetailsScreen(
    catBreedId: String?,
    onBackClick: () -> Unit
) {
    Column {
        Text("Breed details screen : breed id --> $catBreedId")
        Button(onClick = onBackClick) {
            Text("Go back to home")
        }
    }
}