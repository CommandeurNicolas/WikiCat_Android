package com.nicolascommandeur.wikicat.data.remote.models

data class TheCatApiVersionDto(
    val version: String
) {
    companion object {
        const val FALLBACK_API_VERSION: String = "0.0"
    }
}