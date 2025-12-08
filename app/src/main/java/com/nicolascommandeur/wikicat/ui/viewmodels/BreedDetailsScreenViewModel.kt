package com.nicolascommandeur.wikicat.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolascommandeur.wikicat.domain.models.CatBreed
import com.nicolascommandeur.wikicat.domain.usecases.GetCatBreedFromIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class BreedDetailsScreenUiState {
    object Loading: BreedDetailsScreenUiState()
    data class Success(val catBreed: CatBreed?): BreedDetailsScreenUiState()
    data class Error(val errorMessage: String): BreedDetailsScreenUiState()
}

@HiltViewModel
class BreedDetailsScreenViewModel @Inject constructor(
    private val getCatBreedFromIdUseCase: GetCatBreedFromIdUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow<BreedDetailsScreenUiState>(BreedDetailsScreenUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun fetchBreedInfoFromId(catBreedId: String) {
        viewModelScope.launch(Dispatchers.IO) { // TODO: move Dispatcher to repository
            try {
                val breed = getCatBreedFromIdUseCase(catBreedId)
                _uiState.value = BreedDetailsScreenUiState.Success(breed)
            } catch (e: Exception) {
                _uiState.value = BreedDetailsScreenUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun resetUiState() {
        _uiState.value = BreedDetailsScreenUiState.Loading
    }
}