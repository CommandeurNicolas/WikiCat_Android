package com.nicolascommandeur.wikicat.domain.usecases

fun interface ToggleFavoriteUseCase {
    suspend operator fun invoke(catBreedId: String, favorite: Boolean)
}