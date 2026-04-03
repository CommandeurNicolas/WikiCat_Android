package com.nicolascommandeur.wikicat.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nicolascommandeur.wikicat.data.local.entities.CatBreedEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CatBreedDao {
    @Query("SELECT * FROM catBreed")
    fun getCatBreedList(): Flow<List<CatBreedEntity>>

    @Query("SELECT * FROM catBreed WHERE id = :catBreedId")
    fun getCatBreedFromId(catBreedId: String): Flow<CatBreedEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCatBreedList(catBreedList: List<CatBreedEntity>)

    @Query("UPDATE catBreed SET isFavorite = :favorite WHERE id = :catBreedId")
    suspend fun updateFavorite(catBreedId: String, favorite: Boolean)
}