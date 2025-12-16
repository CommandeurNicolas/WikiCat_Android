package com.nicolascommandeur.wikicat.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nicolascommandeur.wikicat.R

@Composable
fun BreedDetailsBackButton(
    iconResourceId: Int,
    onClick: () -> Unit
) {
    IconButton(
        enabled = true,
        onClick = onClick,
        modifier = Modifier
            .clip(CircleShape)
            .border(width = 2.dp, color = MaterialTheme.colorScheme.primary, shape = CircleShape)
            .size(44.dp)
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
    ) {
        Image(
            painter = painterResource(iconResourceId),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .offset(x = (-2).dp)
        )
    }
}

@Preview
@Composable
private fun BreedDetailsBackButton_BackPreview() {
    BreedDetailsBackButton(
        iconResourceId = R.drawable.back_arrow,
        onClick = {}
    )
}