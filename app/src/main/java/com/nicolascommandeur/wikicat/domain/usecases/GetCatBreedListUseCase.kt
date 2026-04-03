package com.nicolascommandeur.wikicat.domain.usecases

import com.nicolascommandeur.wikicat.domain.models.CatBreed
import kotlinx.coroutines.flow.Flow

fun interface GetCatBreedListUseCase {
    operator fun invoke(): Flow<List<CatBreed>>
}