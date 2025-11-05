package com.nicolascommandeur.wikicat.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolascommandeur.wikicat.domain.models.CatBreed
import com.nicolascommandeur.wikicat.domain.usecases.GetCatBreedListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeScreenUiState(
    val breedsList: List<CatBreed> = emptyList(),
    val error: Boolean = false
)

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getCatBreedListUseCase: GetCatBreedListUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadCatBreeds()
    }

    private fun loadCatBreeds() {
        viewModelScope.launch(Dispatchers.IO) {
            val breedsList = try {
                getCatBreedListUseCase()
            } catch(_: Exception) {
                null
            }
            _uiState.update { currentState ->
                currentState.copy(
                    breedsList = breedsList ?: emptyList(),
                    error = breedsList == null
                )
            }
        }
    }
}