package com.nicolascommandeur.wikicat.domain.usecases.impl

import com.nicolascommandeur.wikicat.domain.models.CatBreed
import com.nicolascommandeur.wikicat.domain.repositories.CatBreedRepository
import com.nicolascommandeur.wikicat.domain.usecases.GetCatBreedFromIdUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCatBreedFromIdUseCaseImpl @Inject constructor(
    private val catBreedRepository: CatBreedRepository
) : GetCatBreedFromIdUseCase {
    override operator fun invoke(breedId: String): Flow<CatBreed> = catBreedRepository.getCatBreedFromId(breedId)
}