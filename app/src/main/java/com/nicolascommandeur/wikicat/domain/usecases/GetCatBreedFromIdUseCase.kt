package com.nicolascommandeur.wikicat.domain.usecases

import com.nicolascommandeur.wikicat.domain.models.CatBreed
import kotlinx.coroutines.flow.Flow

fun interface GetCatBreedFromIdUseCase {
    operator fun invoke(breedId: String): Flow<CatBreed>
}