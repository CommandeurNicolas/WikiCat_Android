package com.nicolascommandeur.wikicat.domain.usecases

import com.nicolascommandeur.wikicat.domain.models.CatBreed
import com.nicolascommandeur.wikicat.domain.repositories.CatBreedRepository
import javax.inject.Inject

class GetCatBreedListUseCase @Inject constructor(
    private val catBreedRepository: CatBreedRepository
) {
    @Throws(Exception::class)
    suspend operator fun invoke(): List<CatBreed> = catBreedRepository.getCatBreedList()
}