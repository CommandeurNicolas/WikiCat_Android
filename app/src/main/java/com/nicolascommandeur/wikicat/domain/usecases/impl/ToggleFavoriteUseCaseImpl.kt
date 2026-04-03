package com.nicolascommandeur.wikicat.domain.usecases.impl

import com.nicolascommandeur.wikicat.domain.repositories.CatBreedRepository
import com.nicolascommandeur.wikicat.domain.usecases.ToggleFavoriteUseCase
import javax.inject.Inject

class ToggleFavoriteUseCaseImpl @Inject constructor(
    private val catBreedRepository: CatBreedRepository
) : ToggleFavoriteUseCase {
    override suspend operator fun invoke(catBreedId: String, favorite: Boolean) {
        catBreedRepository.updateFavorite(catBreedId, favorite)
    }
}