package com.johanmos8.presentation.ui.screen.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johanmos8.domain.interactor.GetItemBySearchUseCase
import com.johanmos8.domain.model.ItemDomain
import com.johanmos8.domain.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getItemBySearchUseCase: GetItemBySearchUseCase
) : ViewModel() {


    // UIState
    var uiState by mutableStateOf(UIState())
        private set

    /**
     * Function to get items searching asynchronously a string
     * @param search String to be searched
     */
    private fun getItemsBySearch(search: String) {
        viewModelScope.launch(Dispatchers.IO) {

            getItemBySearchUseCase.invoke(search).collectLatest { result ->
                when (result.status) {
                    Status.LOADING -> {
                        uiState = uiState.copy(isLoading = true)
                    }

                    Status.SUCCESS -> {
                        result.data?.let { items ->
                            uiState = uiState.copy(foundItems = items, isLoading = false)
                        }
                    }

                    Status.ERROR -> {
                        uiState = uiState.copy(
                            errorMessage = result.message.toString(),
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    fun onUIEvent(uiEvent: UIEvent) {
        when (uiEvent) {

            is UIEvent.OnGetItemsBySearch -> getItemsBySearch("bmw")

        }
    }

    sealed class UIEvent {
        data class OnGetItemsBySearch(val search: String) : UIEvent()
    }

    data class UIState(
        var foundItems: List<ItemDomain> = emptyList(),
        var isLoading: Boolean = false,
        var errorMessage: String = ""
    )
}