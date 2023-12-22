package com.johanmos8.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarColors
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.johanmos8.presentation.R
import com.johanmos8.presentation.ui.screen.home.HomeViewModel
import com.johanmos8.presentation.ui.screen.home.ShowList
import com.johanmos8.presentation.ui.theme.BackgroundListColor
import com.johanmos8.presentation.ui.theme.SearchBarColor

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
    var items by rememberSaveable {
        mutableStateOf(mutableListOf<String>())
    }

    Box(
        Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .background(MaterialTheme.colorScheme.background)
            .semantics { isTraversalGroup = true }) {
        SearchBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .semantics { traversalIndex = -1f },
            query = text,
            onQueryChange = { text = it },
            onSearch = {
                active = false
                items.add(text)
                homeViewModel.onUIEvent(HomeViewModel.UIEvent.OnGetItemsBySearch(text))
                text=""
            },
            active = active,
            onActiveChange = {
                active = it
            },
            placeholder = { Text(stringResource(R.string.search_products)) },
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = stringResource(id = R.string.search_icon)
                )
            },
            trailingIcon = {
                if (active) {
                    Icon(
                        modifier = Modifier.clickable {
                            if (text.isNotEmpty()) {
                                text = ""
                            } else {
                                active = false
                            }
                        },
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(id = R.string.close_icon),
                    )
                }
            }
        ) {

            items.forEach {recentSearch ->
                Row(
                    modifier = Modifier.padding(all = 14.dp)
                        .clickable {
                            // Al hacer clic en un elemento reciente, actualiza la barra de búsqueda y realiza la búsqueda
                            text = recentSearch
                            active = false
                            homeViewModel.onUIEvent(HomeViewModel.UIEvent.OnGetItemsBySearch(recentSearch))
                        }
                ) {
                    Icon(
                        modifier = Modifier.padding(end = 10.dp),
                        imageVector = Icons.Default.History,
                        contentDescription = null,
                    )
                    Text(text = recentSearch)
                }
            }
        }


    }


}
