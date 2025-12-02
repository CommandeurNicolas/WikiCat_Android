package com.nicolascommandeur.wikicat.domain.usecases.impl

import com.nicolascommandeur.wikicat.domain.models.CatBreed
import com.nicolascommandeur.wikicat.domain.repositories.CatBreedRepository
import com.nicolascommandeur.wikicat.domain.usecases.GetCatBreedListUseCase
import javax.inject.Inject

class GetCatBreedListUseCaseImpl @Inject constructor(
    private val catBreedRepository: CatBreedRepository
) : GetCatBreedListUseCase {
    @Throws(Exception::class)
    override suspend operator fun invoke(): List<CatBreed> = catBreedRepository.getCatBreedList()
}