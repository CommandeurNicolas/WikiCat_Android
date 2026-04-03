package com.nicolascommandeur.wikicat.di

import android.content.Context
import androidx.room.Room
import com.nicolascommandeur.wikicat.data.local.WikiCatDatabase
import com.nicolascommandeur.wikicat.data.local.dao.CatBreedDao
import com.nicolascommandeur.wikicat.data.local.dao.TheCatApiVersionDao
import com.nicolascommandeur.wikicat.data.remote.api.ApiClient
import com.nicolascommandeur.wikicat.data.remote.api.TheCatApi
import com.nicolascommandeur.wikicat.data.repositories.CatBreedRepositoryImpl
import com.nicolascommandeur.wikicat.domain.repositories.CatBreedRepository
import com.nicolascommandeur.wikicat.domain.usecases.FetchRemoteCatBreedsUseCase
import com.nicolascommandeur.wikicat.domain.usecases.GetCatBreedFromIdUseCase
import com.nicolascommandeur.wikicat.domain.usecases.GetCatBreedListUseCase
import com.nicolascommandeur.wikicat.domain.usecases.ToggleFavoriteUseCase
import com.nicolascommandeur.wikicat.domain.usecases.impl.FetchRemoteCatBreedsUseCaseImpl
import com.nicolascommandeur.wikicat.domain.usecases.impl.GetCatBreedFromIdUseCaseImpl
import com.nicolascommandeur.wikicat.domain.usecases.impl.GetCatBreedListUseCaseImpl
import com.nicolascommandeur.wikicat.domain.usecases.impl.ToggleFavoriteUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideTheCatApi(): TheCatApi = ApiClient.theCatApi

    @Provides
    @Singleton
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

    @Provides
    fun provideCatBreedRepository(api: TheCatApi, apiVersionDao: TheCatApiVersionDao, catBreedDao: CatBreedDao): CatBreedRepository =
        CatBreedRepositoryImpl(api = api, apiVersionDao = apiVersionDao, catBreedDao = catBreedDao)

    @Provides
    fun provideFetchRemoteCatBreedsUseCase(catBreedRepository: CatBreedRepository): FetchRemoteCatBreedsUseCase =
        FetchRemoteCatBreedsUseCaseImpl(catBreedRepository = catBreedRepository)

    @Provides
    fun provideGetCatBreedListUseCase(catBreedRepository: CatBreedRepository): GetCatBreedListUseCase =
        GetCatBreedListUseCaseImpl(catBreedRepository = catBreedRepository)

    @Provides
    fun provideGetCatBreedFromIdUseCase(catBreedRepository: CatBreedRepository): GetCatBreedFromIdUseCase =
        GetCatBreedFromIdUseCaseImpl(catBreedRepository = catBreedRepository)

    @Provides
    fun provideToggleFavoriteUseCase(catBreedRepository: CatBreedRepository): ToggleFavoriteUseCase =
        ToggleFavoriteUseCaseImpl(catBreedRepository = catBreedRepository)
}