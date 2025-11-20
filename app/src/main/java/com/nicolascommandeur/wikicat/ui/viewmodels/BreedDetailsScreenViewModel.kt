package com.nicolascommandeur.wikicat.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolascommandeur.wikicat.domain.models.CatBreed
import com.nicolascommandeur.wikicat.domain.usecases.GetCatBreedFromIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class BreedDetailsScreenUiState(
    val catBreed: CatBreed? = null
)

@HiltViewModel
class BreedDetailsScreenViewModel @Inject constructor(
    private val getCatBreedFromIdUseCase: GetCatBreedFromIdUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(BreedDetailsScreenUiState())
    val uiState = _uiState.asStateFlow()

    fun fetchBreedInfoFromId(catBreedId: String) {
        viewModelScope.launch(Dispatchers.IO) { // TODO: move Dispatcher to repository
            val catBreed = getCatBreedFromIdUseCase(catBreedId)
            _uiState.update { currentState ->
                currentState.copy(catBreed = catBreed)
            }
        }
    }

    fun resetUiState() {
        _uiState.update { currentState ->
            currentState.copy(catBreed = null)
        }
    }
}