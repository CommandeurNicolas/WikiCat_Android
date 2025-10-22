package com.nicolascommandeur.wikicat.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nicolascommandeur.wikicat.data.local.entities.TheCatApiVersionEntity

@Dao
interface TheCatApiVersionDao {
    @Query("SELECT * FROM localVersion")
    fun getLocalVersion(): TheCatApiVersionEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateLocalVersion(apiVersion: TheCatApiVersionEntity)
}