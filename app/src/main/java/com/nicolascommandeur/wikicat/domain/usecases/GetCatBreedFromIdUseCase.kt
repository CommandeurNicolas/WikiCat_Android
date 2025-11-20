package com.nicolascommandeur.wikicat.domain.usecases

import com.nicolascommandeur.wikicat.domain.models.CatBreed
import com.nicolascommandeur.wikicat.domain.repositories.CatBreedRepository
import javax.inject.Inject

class GetCatBreedFromIdUseCase @Inject constructor(
    private val catBreedRepository: CatBreedRepository
) {
    suspend operator fun invoke(breedId: String): CatBreed = catBreedRepository.getCatBreedFromId(breedId)
}