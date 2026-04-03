package com.nicolascommandeur.wikicat.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolascommandeur.wikicat.domain.models.CatBreed
import com.nicolascommandeur.wikicat.domain.usecases.GetCatBreedFromIdUseCase
import com.nicolascommandeur.wikicat.domain.usecases.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface BreedDetailsScreenUiState {
    object Loading: BreedDetailsScreenUiState
    data class Success(val catBreed: CatBreed): BreedDetailsScreenUiState
    data class Error(val errorMessage: String): BreedDetailsScreenUiState
}

@HiltViewModel
class BreedDetailsScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getCatBreedFromIdUseCase: GetCatBreedFromIdUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {
    private val catBreedId: String = checkNotNull(savedStateHandle["catBreedId"])
    val uiState: StateFlow<BreedDetailsScreenUiState> = getCatBreedFromIdUseCase(catBreedId)
        .map <CatBreed, BreedDetailsScreenUiState> { BreedDetailsScreenUiState.Success(it) }
        .catch {
            emit(BreedDetailsScreenUiState.Error(it.message ?: "Unknown error"))
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            BreedDetailsScreenUiState.Loading
        )

    fun onFavoriteClicked(catBreed: CatBreed) {
        viewModelScope.launch {
            toggleFavoriteUseCase(catBreedId = catBreed.id, favorite = !(catBreed.isFavorite ?: false)) // TODO: review favorite = ???
        }
    }
}