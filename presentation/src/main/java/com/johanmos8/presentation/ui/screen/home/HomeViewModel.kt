package com.johanmos8.presentation.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johanmos8.domain.interactor.GetItemBySearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getItemBySearchUseCase: GetItemBySearchUseCase
) : ViewModel() {

    init {
        viewModelScope.launch {

            getItemBySearchUseCase.invoke("MLA1055")
        }
    }
}