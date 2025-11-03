package com.nicolascommandeur.wikicat.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.nicolascommandeur.wikicat.R

// Asap font family
val asapFontFamily = FontFamily(
    // Asap Regular
    Font(R.font.asap_regular, FontWeight.Normal),
    Font(R.font.asap_italic, FontWeight.Normal, FontStyle.Italic),
    // Asap Medium
    Font(R.font.asap_medium, FontWeight.Medium),
    Font(R.font.asap_medium_italic, FontWeight.Medium, FontStyle.Italic),
    // Asap Semi Bold
    Font(R.font.asap_semi_bold, FontWeight.SemiBold),
    Font(R.font.asap_semi_bold_italic, FontWeight.SemiBold, FontStyle.Italic),
    // Asap Bold
    Font(R.font.asap_bold, FontWeight.Bold),
    Font(R.font.asap_bold_italic, FontWeight.Bold, FontStyle.Italic)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = asapFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)