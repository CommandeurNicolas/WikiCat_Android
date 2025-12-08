package com.nicolascommandeur.wikicat.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nicolascommandeur.wikicat.data.local.entities.CatBreedEntity

@Dao
interface CatBreedDao {
    @Query("SELECT * FROM catBreed")
    suspend fun getCatBreedList(): List<CatBreedEntity>

    @Query("SELECT * FROM catBreed WHERE id = :catBreedId")
    suspend fun getCatBreedFromId(catBreedId: String): CatBreedEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCatBreedList(catBreedList: List<CatBreedEntity>)
}