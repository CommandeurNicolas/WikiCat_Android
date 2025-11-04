package com.nicolascommandeur.wikicat.domain.usecases

import com.nicolascommandeur.wikicat.domain.models.CatBreed
import javax.inject.Inject

class GetCatBreedListUseCase @Inject constructor(
    // TODO: call a repository
) {
    suspend operator fun invoke(): List<CatBreed> = emptyList()
}