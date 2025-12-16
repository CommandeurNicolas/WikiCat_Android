package com.nicolascommandeur.wikicat.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nicolascommandeur.wikicat.R
import com.nicolascommandeur.wikicat.domain.models.CatBreed
import com.nicolascommandeur.wikicat.ui.components.BreedDetailsBackButton
import com.nicolascommandeur.wikicat.ui.components.BreedDetailsBreedImage
import com.nicolascommandeur.wikicat.ui.components.BreedDetailsFavoriteButton
import com.nicolascommandeur.wikicat.ui.components.BreedDetailsImportantBox
import com.nicolascommandeur.wikicat.ui.theme.asapFontFamily
import com.nicolascommandeur.wikicat.ui.theme.neutralVariantColor
import com.nicolascommandeur.wikicat.ui.viewmodels.BreedDetailsScreenUiState
import com.nicolascommandeur.wikicat.ui.viewmodels.BreedDetailsScreenViewModel

@Composable
fun BreedDetailsScreen(
    viewModel: BreedDetailsScreenViewModel,
    catBreedId: String?,
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var isFavorite by remember { mutableStateOf(false) } // TODO: get breed value

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
            BreedDetailsScreenUiState.Loading -> BreedDetailsLoadingScreen()
            is BreedDetailsScreenUiState.Success -> {
                val breed = (uiState as BreedDetailsScreenUiState.Success).catBreed

                BreedDetailsSuccessScreen(
                    breed = breed,
                    isFavorite = isFavorite,
                    onBackClick = {
                        onBackClick()
                        viewModel.resetUiState()
                    },
                    onFavoriteClick = { isFavorite = !isFavorite }
                )
            }
            is BreedDetailsScreenUiState.Error -> BreedDetailsErrorScreen()
        }
    }
}

@Composable
private fun BreedDetailsLoadingScreen() {
    // TODO: make and use skeleton loading effect instead of circular progress indicator
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun BreedDetailsSuccessScreen(
    breed: CatBreed?,
    isFavorite: Boolean,
    onBackClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    if (breed != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .scrollable(state = rememberScrollState(), orientation = Orientation.Vertical)
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 16.dp)
        ) {
            // TODO: replace fontFamily, fontSize, fontWeight and font color by Typography parameters

            // Image with back and favorite buttons on top
            Box(
                modifier = Modifier.padding(top = 16.dp)
            ) {
                // Image
                breed.image?.url?.let { imageUrl -> BreedDetailsBreedImage(imageUrl) }

                // Buttons
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp)
                ) {
                    BreedDetailsBackButton(
                        iconResourceId = R.drawable.back_arrow,
                        onClick = onBackClick
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    BreedDetailsFavoriteButton(
                        isFavorite = isFavorite,
                        onClick = onFavoriteClick
                    )
                }
            }

            // Name
            Text(
                text = breed.name,
                fontFamily = asapFontFamily,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .padding(top = 16.dp)
            )

            // Origin
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 8.dp)
            ) {
                Text(
                    text = breed.flagFromCountryCode(),
                    fontFamily = asapFontFamily,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .padding(end = 4.dp)
                )
                Text(
                    text = breed.origin,
                    fontFamily = asapFontFamily,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = neutralVariantColor, // TODO: replace by MaterialTheme
                )
            }

            // Life span / Weight
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 15.dp)
                    .fillMaxWidth()
            ) {
                BreedDetailsImportantBox(
                    topText = "Life span",
                    bottomText = "${breed.lifeSpan} Years"
                )
                BreedDetailsImportantBox(
                    topText = "Weight",
                    bottomText = "${breed.weight.metric} Kg"
                )
            }

            // Description
            Text(
                text = breed.description,
                fontFamily = asapFontFamily,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = neutralVariantColor, // TODO: replace by MaterialTheme
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(top = 15.dp)
            )
        }
    } else {
        // TODO: make a proper "No breed found" view (image + text + back button)
        Text("No breed found")
    }
}

@Composable
private fun BreedDetailsErrorScreen() {
    // TODO: add proper error view
    Text("An error occurred")
}