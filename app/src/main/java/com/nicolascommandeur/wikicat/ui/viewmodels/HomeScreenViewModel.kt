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

sealed class HomeScreenUiState {
    object Loading: HomeScreenUiState()
    data class Success(val breedsList: List<CatBreed>): HomeScreenUiState()
    data class Error(val errorMessage: String): HomeScreenUiState()
}

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getCatBreedListUseCase: GetCatBreedListUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow<HomeScreenUiState>(HomeScreenUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun loadCatBreeds() {
        viewModelScope.launch(Dispatchers.IO) { // TODO: move Dispatcher to repository
            try {
                val breedsList = getCatBreedListUseCase() // breedsList may be empty
                _uiState.value = HomeScreenUiState.Success(breedsList)
            } catch(e: Exception) {
                _uiState.value = HomeScreenUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}