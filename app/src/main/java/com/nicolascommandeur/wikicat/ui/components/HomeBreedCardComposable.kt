package com.nicolascommandeur.wikicat.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nicolascommandeur.wikicat.domain.models.CatBreed
import com.nicolascommandeur.wikicat.toFlagEmoji

@Composable
fun HomeBreedCardComposable(breed: CatBreed) {
    OutlinedCard(
        colors = CardDefaults.cardColors(),
        border = BorderStroke(1.dp, Color.Cyan),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        // TODO: navigate to breed details on click
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            // TODO: add image (use coil ??)
            Text(breed.name)
            Text("${breed.countryCode.toFlagEmoji()} ${breed.origin}")
        }
    }
}