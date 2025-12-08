package com.nicolascommandeur.wikicat.domain.usecases

import com.nicolascommandeur.wikicat.domain.models.CatBreed

fun interface GetCatBreedFromIdUseCase {
    suspend operator fun invoke(breedId: String): CatBreed
}