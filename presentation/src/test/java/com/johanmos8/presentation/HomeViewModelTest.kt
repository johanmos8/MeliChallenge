package com.johanmos8.presentation

import com.johanmos8.domain.interactor.GetCategoriesUseCase
import com.johanmos8.domain.interactor.GetItemBySearchUseCase
import com.johanmos8.domain.model.ItemCategory
import com.johanmos8.domain.model.ItemDetail
import com.johanmos8.domain.util.Resource
import com.johanmos8.presentation.ui.screen.home.HomeViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class HomeViewModelTest {


    private lateinit var getItemBySearchUseCase: GetItemBySearchUseCase
    private lateinit var getCategoriesUseCase: GetCategoriesUseCase
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {

        getItemBySearchUseCase = mock()
        getCategoriesUseCase = mock()
        viewModel = HomeViewModel(getItemBySearchUseCase, getCategoriesUseCase)
    }


    @Test
    fun `given a string when get items by search then update UI state`() = runBlocking{
        // Given
        val searchQuery = "test"
        val listItemsMock: List<ItemDetail> = List(2) { Mockito.mock(ItemDetail::class.java) }


        whenever(getItemBySearchUseCase.invoke(any())).thenReturn(
            MutableStateFlow(Resource.success(listItemsMock))
        )

        // When
        viewModel.onUIEvent(HomeViewModel.UIEvent.OnGetItemsBySearch(searchQuery))

        // Then
        verify(getItemBySearchUseCase).invoke(eq(searchQuery))
        assert(viewModel.uiState.value.isLoading.not())
        assert(viewModel.uiState.value.errorMessage.isEmpty())
        assert(viewModel.uiState.value.foundItems==listItemsMock)
    }

    @Test
    fun `when get categories then update UI state`()= runBlocking{
        // Given

        val expectedCategories =List(2) { Mockito.mock(ItemCategory::class.java) }
        whenever(getCategoriesUseCase.invoke()).thenReturn(
            Resource.success(expectedCategories)
        )

        // When
        viewModel.onUIEvent(HomeViewModel.UIEvent.OnGetCategories)

        // Then
        verify(getCategoriesUseCase).invoke()
        assert(viewModel.uiState.value.isLoading.not())
        assert(viewModel.uiState.value.errorMessage.isEmpty())
        assert(viewModel.uiState.value.categories == expectedCategories)
    }

}
