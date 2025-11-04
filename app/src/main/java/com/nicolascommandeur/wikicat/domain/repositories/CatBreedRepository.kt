package com.nicolascommandeur.wikicat.domain.repositories

import com.nicolascommandeur.wikicat.domain.models.CatBreed

interface CatBreedRepository {
    suspend fun getCatBreedList(): List<CatBreed>
}