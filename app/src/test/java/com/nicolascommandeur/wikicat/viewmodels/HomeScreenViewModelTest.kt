package com.nicolascommandeur.wikicat.viewmodels

import app.cash.turbine.test
import com.nicolascommandeur.wikicat.domain.models.CatBreed
import com.nicolascommandeur.wikicat.domain.usecases.GetCatBreedListUseCase
import com.nicolascommandeur.wikicat.ui.viewmodels.HomeScreenUiState
import com.nicolascommandeur.wikicat.ui.viewmodels.HomeScreenViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Test
import javax.inject.Inject

class HomeScreenViewModelTest {
    private lateinit var viewModel: HomeScreenViewModel

    @Test
    fun `get cat breed list success emits Success`() = runTest {
        val fakeSuccessUseCase = FakeGetCatBreedListUseCase().apply {
            result = listOf(CatBreed.testCatBreed)
            shouldThrow = false
        }
        viewModel = HomeScreenViewModel(fakeSuccessUseCase)

        viewModel.uiState.test {
            // ? Test that uiState is correctly initialized
            val loadingState = awaitItem()
            assert(loadingState is HomeScreenUiState.Loading)

            // ? Load cat breeds to update uiState
            viewModel.loadCatBreeds()

            // ? Test that uiState is correctly updated
            val successState = awaitItem()
            assert(successState is HomeScreenUiState.Success)
            assert((successState as HomeScreenUiState.Success).breedsList == listOf(CatBreed.testCatBreed))
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `get cat breed list error emits Error`() = runTest {
        val errorMessage = "Network failure"
        val fakeErrorUseCase = FakeGetCatBreedListUseCase().apply {
            shouldThrow = true
            exceptionToThrow = Exception(errorMessage)
        }
        viewModel = HomeScreenViewModel(fakeErrorUseCase)

        viewModel.uiState.test {
            // ? Test that uiState is correctly initialized
            val loadingState = awaitItem()
            assert(loadingState is HomeScreenUiState.Loading)

            // ? Load cat breeds to update uiState
            viewModel.loadCatBreeds()

            // ? Test that uiState is correctly updated
            val successState = awaitItem()
            assert(successState is HomeScreenUiState.Error)
            assert((successState as HomeScreenUiState.Error).errorMessage == errorMessage)
            cancelAndIgnoreRemainingEvents()
        }
    }

    class FakeGetCatBreedListUseCase @Inject constructor() : GetCatBreedListUseCase {
        // ! Configure the parameters in each tests
        var result: List<CatBreed> = emptyList()
        var shouldThrow: Boolean = false
        var exceptionToThrow: Exception = Exception("Fake exception")

        @Throws(Exception::class)
        override suspend operator fun invoke(): List<CatBreed> {
            if (shouldThrow) throw exceptionToThrow
            return result
        }
    }
}