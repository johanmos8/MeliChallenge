package com.johanmos8.presentation.ui.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johanmos8.domain.interactor.GetCategoriesUseCase
import com.johanmos8.domain.interactor.GetItemBySearchUseCase
import com.johanmos8.domain.model.ItemCategory
import com.johanmos8.domain.model.ItemDetail
import com.johanmos8.domain.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "HomeViewModel"

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getItemBySearchUseCase: GetItemBySearchUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase

) : ViewModel() {

    private val _searchText = MutableStateFlow("")
    var searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _selectedItem = MutableStateFlow<ItemDetail?>(null)
    val selectedItem: StateFlow<ItemDetail?> = _selectedItem

    // UIState


    private val _uiState = MutableStateFlow(UIState())

    val uiState: StateFlow<UIState> get() = _uiState

    /**
     * Function to get items searching asynchronously a string
     * @param search String to be searched
     */
    private fun getItemsBySearch(search: String) {
        viewModelScope.launch(Dispatchers.IO) {

            getItemBySearchUseCase.invoke(search).collectLatest { result ->
                when (result.status) {
                    Status.LOADING -> {
                        _uiState.value = UIState(isLoading = true)
                    }

                    Status.SUCCESS -> {
                        result.data?.let { items ->
                            _uiState.value = UIState(foundItems = items, isLoading = false)
                        }
                    }

                    Status.ERROR -> {
                        Log.d(TAG, "getItemsBySearch: ${result.message}")
                        _uiState.value = UIState(
                            errorMessage = result.message.toString(),
                            isLoading = false
                        )
                    }
                }
            }
        }
    }


    private fun getCategories() {
        _uiState.value = UIState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            val result = getCategoriesUseCase.invoke()
            when (result.status) {
                Status.LOADING -> {
                    _uiState.value = UIState(isLoading = true)
                }

                Status.SUCCESS -> {
                    result.data?.let { categories ->
                        _uiState.value = UIState(categories = categories, isLoading = false)
                    }
                }

                Status.ERROR -> {
                    Log.d(TAG, "getCategories: ${result.message}")
                    _uiState.value = UIState(
                        errorMessage = result.message.toString(),
                        isLoading = false
                    )
                }
            }
        }
    }

    fun onUIEvent(uiEvent: UIEvent) {
        when (uiEvent) {

            is UIEvent.OnGetItemsBySearch -> getItemsBySearch(uiEvent.search)
            is UIEvent.OnGetCategories -> getCategories()
        }
    }

    sealed class UIEvent {
        data class OnGetItemsBySearch(val search: String) : UIEvent()
        object OnGetCategories : UIEvent()
    }

    data class UIState(
        var categories: List<ItemCategory> = emptyList(),
        var foundItems: List<ItemDetail> = emptyList(),
        var isLoading: Boolean = false,
        var errorMessage: String = ""
    )
}