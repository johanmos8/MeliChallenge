package com.johanmos8.presentation.ui.screen.detail

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.johanmos8.domain.model.ItemDetail
import com.johanmos8.presentation.R
import com.johanmos8.presentation.ui.component.ErrorMessage
import com.johanmos8.presentation.ui.component.LoadingIndicator
import com.johanmos8.presentation.ui.component.PicturesSlider
import com.johanmos8.presentation.ui.screen.home.HomeViewModel
import com.johanmos8.presentation.util.formatPrice
import com.johanmos8.presentation.util.translateCondition

@Composable
fun DetailScreen(
    navHostController: NavHostController,
    itemId: String,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    Log.d("DetailScreen", "itemId: $itemId")
    LaunchedEffect(key1 = true) {
        viewModel.onUIEvent(DetailsViewModel.UIEvent.OnGetItemsDetails(itemId))

    }
    val state = viewModel.state.collectAsState()
    val item = state.value.item

    item?.let { item ->
        ItemDetailScreen(item, navHostController)
    }

    if (!state.value.errorMessage.isNullOrEmpty()) {
        //TODO("Show error specific message depending on error type")
        ErrorMessage(errorMessage = stringResource(R.string.unexpected_error),
            modifier = Modifier,
            onRetry = { viewModel.onUIEvent(DetailsViewModel.UIEvent.OnGetItemsDetails(itemId)) }
        )
    }
    LoadingIndicator(state.value.isLoading)

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemDetailScreen(item: ItemDetail, navHostController: NavHostController) {
    Scaffold(

        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navHostController.popBackStack()
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    //containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                if (item.freeShipping) {
                    Text(
                        modifier = Modifier.padding(vertical = 8.dp),
                        text = stringResource(R.string.free_shipping),
                        style = TextStyle(
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.primary
                        ),
                    )
                }


                Spacer(modifier = Modifier.height(16.dp))

                item.picturesUrl?.let { PicturesSlider(it) }


                // Display item details
                Spacer(modifier = Modifier.height(16.dp))
                item.price?.let {

                    Text(
                        text = stringResource(
                            R.string.item_price,
                            it.formatPrice(item.currencyId)
                        ),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .padding(bottom = 2.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(R.string.item_status, item.translateCondition()),
                        modifier = Modifier.align(Alignment.Bottom),
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    if (item.acceptsMercadoPago == true) {
                        //MercadoPagoLabel()
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))


                item.location?.let { location ->

                    Divider(modifier = Modifier.fillMaxWidth())

                    Row(
                        modifier = Modifier.padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.LocationOn,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                        )
                        Text(
                            text = location,
                            style = TextStyle(
                                color = Blue,
                                fontWeight = FontWeight.Light,
                                fontSize = 18.sp,
                            ),
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                }
                Divider(modifier = Modifier.fillMaxWidth())
                // Button to add to cart or perform other actions
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = { /* Handle button click */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),

                    ) {
                    Text(text = "Add to Cart (ToDo)")
                    //TODO("Add item to cart")
                    //TODO("change for FAP" )
                }
            }
        }
    )
}
