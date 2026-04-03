package com.nicolascommandeur.wikicat.domain.usecases.impl

import com.nicolascommandeur.wikicat.domain.models.CatBreed
import com.nicolascommandeur.wikicat.domain.repositories.CatBreedRepository
import com.nicolascommandeur.wikicat.domain.usecases.GetCatBreedListUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCatBreedListUseCaseImpl @Inject constructor(
    private val catBreedRepository: CatBreedRepository
) : GetCatBreedListUseCase {
    @Throws(Exception::class)
    override operator fun invoke(): Flow<List<CatBreed>> = catBreedRepository.getCatBreedList()
}