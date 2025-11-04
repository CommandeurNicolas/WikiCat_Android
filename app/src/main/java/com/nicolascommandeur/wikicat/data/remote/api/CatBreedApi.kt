package com.nicolascommandeur.wikicat.data.remote.api

import com.nicolascommandeur.wikicat.data.remote.models.CatBreedDto
import com.nicolascommandeur.wikicat.data.remote.models.TheCatApiVersionDto
import retrofit2.http.GET

interface TheCatApi {
    @GET(".") // Nothing to add to get the api version
    suspend fun getApiVersion(): TheCatApiVersionDto

    @GET("breeds")
    suspend fun getBreeds(): List<CatBreedDto>
}