package com.johanmos8.presentation.ui.screen.home

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.johanmos8.domain.model.ItemDetail
import com.johanmos8.presentation.ui.component.CardItem
import com.johanmos8.presentation.ui.component.LoadingIndicator
import com.johanmos8.presentation.ui.component.SearchBarComponent
import com.johanmos8.presentation.ui.navigation.NavigationScreen


@Composable
fun HomeScreen(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    HomeScreenContent(
        navHostController = navHostController,
        viewModel = homeViewModel
    )

}

@Composable
fun HomeScreenContent(
    viewModel: HomeViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    val onItemClick: (String) -> Unit = { itemId ->
        Log.d("HomeScreen", "onItemClick: $itemId")
        navHostController.navigate(NavigationScreen.DetailScreen.createRoute(itemId))
    }
    val uiState = viewModel.uiState.collectAsState()
    SearchBarComponent(homeViewModel = viewModel, onItemClick = onItemClick)
    LoadingIndicator(uiState.value.isLoading)
    if (uiState.value.errorMessage != null) {
        //ErrorScreen(uiState.value.errorMessage)
    }
}

@Composable
fun ShowList(
    onItemClick: (String) -> Unit,
    modifier: Modifier,
    items: List<ItemDetail>
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp)
    ) {
        items(items) { item ->
            CardItem(
                modifier = Modifier

                    .padding(8.dp),
                item = item,
                onItemClick = onItemClick
            )
        }
    }
}