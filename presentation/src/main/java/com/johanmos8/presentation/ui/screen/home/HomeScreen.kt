package com.johanmos8.presentation.ui.screen.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.johanmos8.domain.model.ItemDetail
import com.johanmos8.presentation.R
import com.johanmos8.presentation.ui.component.CardItem
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

    Scaffold(
        topBar = {
            SearchBarComponent(homeViewModel = viewModel, onItemClick = onItemClick)
        },

        ) {
        if (uiState.value.foundItems.isEmpty()) {
            EmptySearchResultMessage()
        } else {

            ShowList(
                onItemClick = onItemClick,
                modifier = Modifier
                    .padding(it)
                    .background(BackgroundListColor),
                items = uiState.value.foundItems
            )
        }
    }

    if (!uiState.value.errorMessage.isNullOrEmpty()) {
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