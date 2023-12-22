package com.johanmos8.presentation.ui.screen.detail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johanmos8.domain.interactor.GetItemDetailsUseCase
import com.johanmos8.domain.model.ItemDetail
import com.johanmos8.domain.util.Status
import com.johanmos8.presentation.ui.screen.home.HomeViewModel
import com.johanmos8.presentation.ui.screen.home.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "DetailsViewModel"
@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getItemDetailUseCase: GetItemDetailsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ItemDetailsUiState())
    val state: StateFlow<ItemDetailsUiState> = _state

    private fun getItemDetail(itemId: String) {
        Log.d("DetailsViewModel", "getItemDetail: $itemId")
        _state.value = ItemDetailsUiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            val result = getItemDetailUseCase.invoke(itemId)
            when (result.status) {
                Status.LOADING -> {
                    _state.value = ItemDetailsUiState(isLoading = true)

                }

                Status.SUCCESS -> {
                    _state.value = ItemDetailsUiState(item = result.data)

                }

                Status.ERROR -> {
                    Log.d(TAG, "getItemDetail: ${result.message}")

                    _state.value = ItemDetailsUiState(errorMessage = result.message.toString())
                }

            }

        }
    }

    fun onUIEvent(uiEvent: UIEvent) {
        when (uiEvent) {

            is UIEvent.OnGetItemsDetails -> getItemDetail(uiEvent.itemId)

        }
    }

    sealed class UIEvent {
        data class OnGetItemsDetails(val itemId: String) : UIEvent()

    }

    data class ItemDetailsUiState(
        val isLoading: Boolean = false,
        val item: ItemDetail? = null,
        val errorMessage: String? = "",
    )
}