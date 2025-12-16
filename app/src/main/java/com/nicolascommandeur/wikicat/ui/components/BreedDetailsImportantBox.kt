package com.nicolascommandeur.wikicat.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nicolascommandeur.wikicat.ui.theme.asapFontFamily
import com.nicolascommandeur.wikicat.ui.theme.neutralVariantColor

@Composable
fun BreedDetailsImportantBox(
    topText: String,
    bottomText: String
) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .size(100.dp)
            .clip(RoundedCornerShape(24.dp))
            .border(width = 2.dp, color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(24.dp))
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
    ) {
        Text(
            text = topText,
            fontFamily = asapFontFamily,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = neutralVariantColor // TODO: replace by MaterialTheme
        )
        Text(
            text = bottomText,
            fontFamily = asapFontFamily,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}