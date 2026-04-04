package com.nicolascommandeur.wikicat.data.repositories

import android.util.Log
import com.nicolascommandeur.wikicat.data.local.dao.CatBreedDao
import com.nicolascommandeur.wikicat.data.local.dao.TheCatApiVersionDao
import com.nicolascommandeur.wikicat.data.mappers.toDomain
import com.nicolascommandeur.wikicat.data.mappers.toEntity
import com.nicolascommandeur.wikicat.data.remote.api.TheCatApi
import com.nicolascommandeur.wikicat.data.remote.models.TheCatApiVersionDto
import com.nicolascommandeur.wikicat.domain.models.CatBreed
import com.nicolascommandeur.wikicat.domain.repositories.CatBreedRepository
import com.nicolascommandeur.wikicat.utils.VersionUtil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
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
    override suspend fun fetchRemoteCatBreeds() {
        try {
            val localVersion = apiVersionDao.getLocalVersion()
            val apiVersion = api.getApiVersion().toEntity()

            if (
                apiVersion.version == TheCatApiVersionDto.FALLBACK_API_VERSION
                || VersionUtil.isApiVersionGreater(apiVersion = apiVersion.version, localVersion = localVersion?.version ?: TheCatApiVersionDto.FALLBACK_API_VERSION)
            ) {
                // Api version is greater so fetch api breeds
                // 1. Update local version to api version
                apiVersionDao.updateLocalVersion(apiVersion)
                // 2. Fetch api breeds
                val apiBreeds = api.getBreeds()
                // 3. Retrieve already saved local breeds
                val localBreeds = catBreedDao.getCatBreedList()
                val localMap = localBreeds.associateBy { it.id }

                // 4. Merge api and local breeds to keep local fields as they are
                val mergedBreeds = apiBreeds.map { dto ->
                    dto.toEntity(localMap[dto.id]?.isFavorite)
                }

                // 5. Update local breeds
                catBreedDao.insertCatBreedList(mergedBreeds)
            }
        } catch (e: Exception) {
            Log.e(TAG, "getCatBreedList:CATCH --> ${e.message}")
            // Most likely an internet connection issues so fetch local data
            val localData = catBreedDao.getCatBreedListFlow().cancellable()
            // If local data are empty then throw the error
            if(localData.first().isEmpty()) throw e
        }
    }
    override fun getCatBreedList(): Flow<List<CatBreed>> {
        return catBreedDao.getCatBreedListFlow().map { entities -> entities.map { it.toDomain() } }
    }

    override fun getCatBreedFromId(catBreedId: String): Flow<CatBreed> {
        return catBreedDao.getCatBreedFromId(catBreedId).map { it.toDomain() }
    }

    override suspend fun updateFavorite(catBreedId: String, favorite: Boolean) {
        catBreedDao.updateFavorite(catBreedId, favorite)
    }
}