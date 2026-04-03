package com.nicolascommandeur.wikicat.domain.repositories

import com.nicolascommandeur.wikicat.domain.models.CatBreed
import kotlinx.coroutines.flow.Flow

interface CatBreedRepository {

    @Throws(Exception::class)
    suspend fun fetchRemoteCatBreeds()

    fun getCatBreedList(): Flow<List<CatBreed>>

    fun getCatBreedFromId(catBreedId: String): Flow<CatBreed>

    suspend fun updateFavorite(catBreedId: String, favorite: Boolean)
}