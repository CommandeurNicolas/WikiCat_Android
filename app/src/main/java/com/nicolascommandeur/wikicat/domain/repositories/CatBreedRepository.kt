package com.nicolascommandeur.wikicat.domain.repositories

import com.nicolascommandeur.wikicat.domain.models.CatBreed

interface CatBreedRepository {
    @Throws(Exception::class)
    suspend fun getCatBreedList(): List<CatBreed>

    suspend fun getCatBreedFromId(breedId: String): CatBreed?
}