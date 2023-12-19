package com.johanmos8.presentation.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.johanmos8.presentation.R
import com.johanmos8.presentation.ui.screen.home.HomeViewModel
import com.johanmos8.presentation.ui.screen.home.ShowList

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun SearchBarComponent(
    homeViewModel: HomeViewModel = hiltViewModel(),
    onItemClick: (String) -> Unit,
) {
    var text by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }
    val searchText by homeViewModel.searchText.collectAsState()
    val isSearching by homeViewModel.isSearching.collectAsState()


    Scaffold(
        topBar = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .semantics { isTraversalGroup = true }) {
                SearchBar(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .semantics { traversalIndex = -1f },
                    query = text,
                    onQueryChange = { text = it },
                    onSearch = {
                        active = false
                        homeViewModel.onUIEvent(HomeViewModel.UIEvent.OnGetItemsBySearch(text))
                    },
                    active = active,
                    onActiveChange = {
                        active = it
                    },
                    placeholder = { Text(stringResource(R.string.search_products)) },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    trailingIcon = { Icon(Icons.Default.MoreVert, contentDescription = null) },
                ) {

                }


            }
        }
    ) {
        val uiState = homeViewModel.uiState.collectAsState()
        ShowList(
            onItemClick = onItemClick,
            modifier = Modifier.padding(it),
            items = uiState.value.foundItems
        )
    }

}
