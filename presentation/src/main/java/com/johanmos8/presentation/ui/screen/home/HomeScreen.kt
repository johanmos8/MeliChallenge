package com.johanmos8.presentation.ui.screen.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.johanmos8.domain.model.ItemDomain
import com.johanmos8.presentation.ui.component.CardItem
import com.johanmos8.presentation.ui.component.LoadingIndicator
import com.johanmos8.presentation.ui.screen.home.HomeViewModel.UIState


@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        homeViewModel.onUIEvent(HomeViewModel.UIEvent.OnGetItemsBySearch("x"))

    }
    HomeScreenContent(
        uiState = homeViewModel.uiState,
        viewModel = homeViewModel
    )

}

@Composable
fun HomeScreenContent(
    uiState: UIState = UIState(),
    viewModel: HomeViewModel = hiltViewModel(),
) {
    ShowList(items = uiState.foundItems)
    LoadingIndicator(uiState.isLoading)
}

@Composable
fun ShowList(items: List<ItemDomain>) {
    LazyColumn(
        contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp)
    ) {
        items(items) { item ->
            CardItem(
                modifier = Modifier.padding(8.dp),
                item = item,

                )
        }
    }
}