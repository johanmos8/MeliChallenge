package com.johanmos8.presentation.ui.screen.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.johanmos8.domain.model.ItemDetail
import com.johanmos8.presentation.R
import com.johanmos8.presentation.ui.component.CardItem
import com.johanmos8.presentation.ui.component.CategoryList
import com.johanmos8.presentation.ui.component.EmptySearchResultMessage
import com.johanmos8.presentation.ui.component.ErrorMessage
import com.johanmos8.presentation.ui.component.LoadingIndicator
import com.johanmos8.presentation.ui.component.SearchBarComponent
import com.johanmos8.presentation.ui.navigation.NavigationScreen
import com.johanmos8.presentation.ui.theme.BackgroundListColor


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

    LaunchedEffect(key1 = true) {
        viewModel.onUIEvent(HomeViewModel.UIEvent.OnGetCategories)
    }
    Scaffold(
        topBar = {
            SearchBarComponent(homeViewModel = viewModel, onItemClick = onItemClick)
        },

        ) {
        Log.d("HomeScreen", "it: ${uiState.value.foundItems.isEmpty()}")
        if (uiState.value.foundItems.isNotEmpty()) {
            ShowList(
                onItemClick = onItemClick,
                modifier = Modifier
                    .padding(it)
                    .background(BackgroundListColor),
                items = uiState.value.foundItems
            )
        } else if (uiState.value.categories.isNotEmpty()) {
            CategoryList( modifier = Modifier
                .padding(it)
                .background(BackgroundListColor),categories = uiState.value.categories, onCategoryClicked = {/*TODO*/ })
        } else if(!uiState.value.isLoading &&uiState.value.errorMessage.isEmpty()) {
            EmptySearchResultMessage()
        }
    }
    if (uiState.value.errorMessage.isNotEmpty()) {
        ErrorMessage(
            errorMessage = stringResource(R.string.unexpected_error),
            modifier = Modifier,
            onRetry = { viewModel.onUIEvent(HomeViewModel.UIEvent.OnGetItemsBySearch("Samsung")) }
        )
    }

    LoadingIndicator(uiState.value.isLoading)

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