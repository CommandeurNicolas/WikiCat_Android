package com.nicolascommandeur.wikicat.domain.usecases

import com.nicolascommandeur.wikicat.domain.models.CatBreed

fun interface GetCatBreedListUseCase {
    suspend operator fun invoke(): List<CatBreed>
}