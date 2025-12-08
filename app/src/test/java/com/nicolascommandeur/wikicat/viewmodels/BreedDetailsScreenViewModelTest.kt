package com.nicolascommandeur.wikicat.viewmodels

import app.cash.turbine.test
import com.nicolascommandeur.wikicat.domain.models.CatBreed
import com.nicolascommandeur.wikicat.domain.usecases.GetCatBreedFromIdUseCase
import com.nicolascommandeur.wikicat.ui.viewmodels.BreedDetailsScreenUiState
import com.nicolascommandeur.wikicat.ui.viewmodels.BreedDetailsScreenViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Test
import javax.inject.Inject

class BreedDetailsScreenViewModelTest {
    private lateinit var viewModel: BreedDetailsScreenViewModel

    @Test
    fun `get breed from id success emits Success`() = runTest {
        val fakeSuccessUseCase = FakeGetCatBreedFromIdUseCase().apply {
            result = CatBreed.testCatBreed
            shouldThrow = false
        }

        viewModel = BreedDetailsScreenViewModel(fakeSuccessUseCase)

        viewModel.uiState.test {
            // ? Test that uiState is correctly initialized
            val loadingStage = awaitItem()
            assert(loadingStage is BreedDetailsScreenUiState.Loading)

            // ? Retrieve cat breed from id to update uiState
            viewModel.fetchBreedInfoFromId("") // No need to specify the breed id, the use case result is set above

            // ? Test that uiState is correctly updated
            val successState = awaitItem()
            assert(successState is BreedDetailsScreenUiState.Success)
            assert((successState as BreedDetailsScreenUiState.Success).catBreed != null)
            assert(successState.catBreed == CatBreed.testCatBreed)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `get breed from id error emits Error`() = runTest {
        val errorMessage = ""
        val fakeErrorUseCase = FakeGetCatBreedFromIdUseCase().apply {
            shouldThrow = true
            exceptionToThrow = Exception(errorMessage)
        }

        viewModel = BreedDetailsScreenViewModel(fakeErrorUseCase)

        viewModel.uiState.test {
            // ? Test that uiState is correctly initialized
            val loadingState = awaitItem()
            assert(loadingState is BreedDetailsScreenUiState.Loading)

            // ? Retrieve cat breed from id to update uiState
            viewModel.fetchBreedInfoFromId("")  // No need to specify the breed id, the use case result will be an error

            // ? Test that uiState is correctly updated
            val errorState = awaitItem()
            assert(errorState is BreedDetailsScreenUiState.Error)
            assert((errorState as BreedDetailsScreenUiState.Error).errorMessage == errorMessage)

            cancelAndIgnoreRemainingEvents()
        }
    }

    class FakeGetCatBreedFromIdUseCase @Inject constructor() : GetCatBreedFromIdUseCase {
        // ! Configure the parameters in each tests
        var result: CatBreed? = null
        var shouldThrow: Boolean = false
        var exceptionToThrow: Exception = Exception("Fake exception")
        override suspend fun invoke(breedId: String): CatBreed? {
            if (shouldThrow) throw exceptionToThrow
            return result
        }
    }
}