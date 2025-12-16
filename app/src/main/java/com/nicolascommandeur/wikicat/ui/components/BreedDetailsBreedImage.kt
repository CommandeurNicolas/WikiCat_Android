package com.nicolascommandeur.wikicat.ui.components

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter

@Composable
fun BreedDetailsBreedImage(imageUrl: String) {
    AsyncImage(
        model = imageUrl,
        contentDescription = null,
        onState = { imageState ->
            when (imageState) {
                is AsyncImagePainter.State.Loading -> {
                    Log.d("Details-AsyncImage", "LOADING")
                }
                is AsyncImagePainter.State.Empty -> {
                    Log.d("Details-AsyncImage", "EMPTY")
                }
                is AsyncImagePainter.State.Success -> {
                    Log.d("Details-AsyncImage", "SUCCESS")
                }
                is AsyncImagePainter.State.Error -> {
                    Log.d("Details-AsyncImage", "ERROR ---> ${imageState.result.throwable.message}")
                }
            }
        },
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
    )
}