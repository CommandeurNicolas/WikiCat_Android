package com.nicolascommandeur.wikicat.domain.usecases.impl

import com.nicolascommandeur.wikicat.domain.repositories.CatBreedRepository
import com.nicolascommandeur.wikicat.domain.usecases.FetchRemoteCatBreedsUseCase
import javax.inject.Inject

class FetchRemoteCatBreedsUseCaseImpl @Inject constructor(
    private val catBreedRepository: CatBreedRepository
) : FetchRemoteCatBreedsUseCase {
    override suspend fun invoke() = catBreedRepository.fetchRemoteCatBreeds()
}