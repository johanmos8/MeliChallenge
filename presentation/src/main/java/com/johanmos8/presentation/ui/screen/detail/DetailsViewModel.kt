package com.johanmos8.presentation.ui.screen.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johanmos8.domain.interactor.GetItemDetailsUseCase
import com.johanmos8.domain.model.ItemDetail
import com.johanmos8.domain.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getItemDetailUseCase: GetItemDetailsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(ItemDetailsUiState())
    val state: State<ItemDetailsUiState> = _state

    fun getItemDetail(itemId: String) {
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
                    _state.value = ItemDetailsUiState(error =  result.message.toString())
                }

            }

        }
    }

    data class ItemDetailsUiState(
        val isLoading: Boolean = false,
        val item: ItemDetail? = null,
        val error: String? = "",
    )
}