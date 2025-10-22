package com.nicolascommandeur.wikicat.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nicolascommandeur.wikicat.data.local.converters.DatabaseConverters
import com.nicolascommandeur.wikicat.data.local.dao.CatBreedDao
import com.nicolascommandeur.wikicat.data.local.dao.TheCatApiVersionDao
import com.nicolascommandeur.wikicat.data.local.entities.CatBreedEntity
import com.nicolascommandeur.wikicat.data.local.entities.TheCatApiVersionEntity

@Database(
    entities = [CatBreedEntity::class, TheCatApiVersionEntity::class],
    version = 1
)
@TypeConverters(DatabaseConverters::class)
abstract class WikiCatDatabase : RoomDatabase() {
    abstract fun theCatApiVersionDao(): TheCatApiVersionDao
    abstract fun catBreedDao(): CatBreedDao
}