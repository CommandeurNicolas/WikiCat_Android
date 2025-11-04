package com.nicolascommandeur.wikicat.domain.models

import androidx.compose.runtime.Immutable

@Immutable
class TheCatApiVersion(
    val version: String
) {
    companion object {
        const val FALLBACK_API_VERSION: String = "0.0"
    }
}