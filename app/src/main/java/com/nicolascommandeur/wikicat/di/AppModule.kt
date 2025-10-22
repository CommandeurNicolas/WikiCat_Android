package com.nicolascommandeur.wikicat.di

import android.content.Context
import androidx.room.Room
import com.nicolascommandeur.wikicat.data.local.WikiCatDatabase
import com.nicolascommandeur.wikicat.data.local.dao.CatBreedDao
import com.nicolascommandeur.wikicat.data.local.dao.TheCatApiVersionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): WikiCatDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = WikiCatDatabase::class.java,
            name = "wiki_cat"
        ).build()
    }

    @Provides
    fun provideTheCatApiVersionDao(database: WikiCatDatabase): TheCatApiVersionDao = database.theCatApiVersionDao()

    @Provides
    fun provideCatBreedDao(database: WikiCatDatabase): CatBreedDao = database.catBreedDao()
}