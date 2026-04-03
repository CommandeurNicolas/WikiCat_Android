package com.nicolascommandeur.wikicat.domain.usecases

fun interface FetchRemoteCatBreedsUseCase {
    suspend operator fun invoke()
}