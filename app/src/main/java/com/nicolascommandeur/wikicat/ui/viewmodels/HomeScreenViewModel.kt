package com.nicolascommandeur.wikicat.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolascommandeur.wikicat.domain.models.CatBreed
import com.nicolascommandeur.wikicat.domain.usecases.FetchRemoteCatBreedsUseCase
import com.nicolascommandeur.wikicat.domain.usecases.GetCatBreedListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface HomeScreenUiState {
    object Loading: HomeScreenUiState
    object Empty: HomeScreenUiState
    data class Success(val breedsList: List<CatBreed>): HomeScreenUiState
    data class Error(val errorMessage: String): HomeScreenUiState
}

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val fetchRemoteCatBreedsUseCase: FetchRemoteCatBreedsUseCase,
    getCatBreedListUseCase: GetCatBreedListUseCase,
) : ViewModel() {

    val uiState: StateFlow<HomeScreenUiState> = getCatBreedListUseCase()
        .map { breeds ->
            if (breeds.isEmpty()) HomeScreenUiState.Empty
            else HomeScreenUiState.Success(breeds)
        }
        .catch {
            emit(HomeScreenUiState.Error(it.message ?: "Unknown error"))
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            HomeScreenUiState.Loading
        )

    init {
        fetchRemoteBreeds()
    }

    private fun fetchRemoteBreeds() {
        viewModelScope.launch(Dispatchers.IO) { fetchRemoteCatBreedsUseCase() }
    }
}