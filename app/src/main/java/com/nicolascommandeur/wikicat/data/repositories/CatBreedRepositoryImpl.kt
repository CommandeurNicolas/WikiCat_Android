package com.nicolascommandeur.wikicat.data.repositories

import android.util.Log
import com.nicolascommandeur.wikicat.utils.VersionUtil
import com.nicolascommandeur.wikicat.data.local.dao.CatBreedDao
import com.nicolascommandeur.wikicat.data.local.dao.TheCatApiVersionDao
import com.nicolascommandeur.wikicat.data.mappers.toDomain
import com.nicolascommandeur.wikicat.data.mappers.toEntity
import com.nicolascommandeur.wikicat.data.remote.api.TheCatApi
import com.nicolascommandeur.wikicat.domain.models.CatBreed
import com.nicolascommandeur.wikicat.domain.models.TheCatApiVersion
import com.nicolascommandeur.wikicat.domain.repositories.CatBreedRepository
import javax.inject.Inject

class CatBreedRepositoryImpl @Inject constructor(
    private val api: TheCatApi,
    private val apiVersionDao: TheCatApiVersionDao,
    private val catBreedDao: CatBreedDao
) : CatBreedRepository {
    companion object {
        private const val TAG = "CatBreedRepositoryImpl"
    }

    @Throws(Exception::class)
    override suspend fun getCatBreedList(): List<CatBreed> {
        return try {
            val localVersion = apiVersionDao.getLocalVersion()?.toDomain()
            val apiVersion = api.getApiVersion().toDomain()

            if (
                apiVersion.version == TheCatApiVersion.FALLBACK_API_VERSION
                || VersionUtil.isApiVersionGreater(apiVersion = apiVersion.version, localVersion = localVersion?.version ?: TheCatApiVersion.FALLBACK_API_VERSION)
            ) {
                // Api version is greater so fetch api breeds
                // 1. Update local version to api version
                apiVersionDao.updateLocalVersion(apiVersion.toEntity())
                // 2. Fetch api breeds
                val apiBreeds = api.getBreeds()
                val breeds = apiBreeds.map { it.toDomain() }
                // 3. Update local breeds
                catBreedDao.insertCatBreedList(breeds.map { it.toEntity() })
                // 4. Return breeds list
                breeds
            } else {
                // Local version is greater or equal so no need to fetch api breeds
                catBreedDao.getCatBreedList().map { it.toDomain() }
            }
        } catch (e: Exception) {
            Log.e(TAG, "getCatBreedList:CATCH --> ${e.message}")
            // Most likely an internet connection issues so fetch local data
            val localData = catBreedDao.getCatBreedList()
            // If local data are empty then throw the error
            if(localData == emptyList<CatBreed>()) throw e
            // Else return mapped local data
            localData.map { it.toDomain() }
        }
    }

    override suspend fun getCatBreedFromId(breedId: String): CatBreed? {
        return catBreedDao.getCatBreedFromId(breedId)?.toDomain()
    }
}