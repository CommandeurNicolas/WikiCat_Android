package com.nicolascommandeur.wikicat.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import com.nicolascommandeur.wikicat.R
import com.nicolascommandeur.wikicat.domain.models.CatBreed
import com.nicolascommandeur.wikicat.ui.theme.asapFontFamily
import com.nicolascommandeur.wikicat.utils.innerBorder

@Composable
fun HomeBreedCardComposable(
    breed: CatBreed,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.White)
            .innerBorder(strokeWidth = 2.dp, color = MaterialTheme.colorScheme.primary, cornerRadiusDp = 6.dp)
            .clip(RoundedCornerShape(6.dp))
            .then(modifier) // Adding clickable after changing appearance to match the box's shape
    ) {
        // Breed infos
        Column(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 6.dp)
        ) {
            Spacer(modifier = Modifier.height(180.dp)) // ? Image's height

            // Breed name + favorite heart
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = breed.name,
                    fontFamily = asapFontFamily,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onBackground
                )
                if (breed.isFavorite == true) {
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        painter = painterResource(R.drawable.heart_filled),
                        contentDescription = null,
                        modifier = Modifier
                            .size(16.dp)
                    )
                }
            }

            // Country flag + country name
            Row {
                breed.countryCode?.let { countryCode ->
                    Text(
                        text = breed.flagFromCountryCode(),
                        fontFamily = asapFontFamily,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier
                            .padding(end = 4.dp)
                    )
                }
                breed.origin?.let { origin ->
                    Text(
                        text = origin,
                        fontFamily = asapFontFamily,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }

        // Breed image
        breed.image?.url?.let { imageUrl ->
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
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(6.dp))
            )
        }
    }
}