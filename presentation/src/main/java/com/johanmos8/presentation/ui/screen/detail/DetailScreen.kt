package com.johanmos8.presentation.ui.screen.detail

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.johanmos8.domain.model.ItemDetail
import com.johanmos8.presentation.R
import com.johanmos8.presentation.ui.component.LoadingIndicator
import com.johanmos8.presentation.ui.component.PicturesSlider
import com.johanmos8.presentation.ui.screen.home.HomeViewModel
import com.johanmos8.presentation.util.convertToHttps
import com.johanmos8.presentation.util.formatPrice
import com.johanmos8.presentation.util.getAsyncImageModel

@Composable
fun DetailScreen(
    itemId: String,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    Log.d("DetailScreen", "itemId: $itemId")
    LaunchedEffect(key1 = true) {
        viewModel.getItemDetail(itemId)
    }
    val state = viewModel.state.value
    val item = state.item
    Log.d("DetailScreen", "DetailScreen: $item")
    LoadingIndicator(state.isLoading)

    item?.let { item ->

        itemDetailScreen(item)
        //Details(item)

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun itemDetailScreen(item: ItemDetail) {
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
                    IconButton(onClick = { /* Handle back button */ }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },

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
                            fontSize = 16.sp,
                            color = Green
                        ),
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(bottom = 2.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(R.string.item_status, item.condition),
                        modifier = Modifier.align(Alignment.Bottom),
                        style = TextStyle(
                            fontSize = 16.sp
                        )
                    )
                    if (item.acceptsMercadoPago == true) {
                        //MercadoPagoLabel()
                    }
                }
                item.picturesUrl?.let { PicturesSlider(it) }


                // Display item details
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Price: ${item.price?.formatPrice(item.currencyId)} ",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Condition: ${item.condition}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Divider(modifier = Modifier.fillMaxWidth())
                Row(
                    modifier = Modifier.padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = "Location Icon",
                        tint = Blue,
                    )
                    Text(
                        text = item.location ?: "Unknown",
                        style = TextStyle(
                            color = Blue,
                            fontWeight = FontWeight.Light,
                            fontSize = 16.sp,
                        ),
                    )
                }
                Divider(modifier = Modifier.fillMaxWidth())

                // Button to add to cart or perform other actions
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = { /* Handle button click */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                ) {
                    Text(text = "Add to Cart (TODO)")
                }
            }
        }
    )
}

@Composable
fun Details(
    product: ItemDetail
) {
    Column(
    ) {
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(bottomStart = 46.dp, bottomEnd = 46.dp))
                //.background(BackgroundTitleProductColor)
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
        ) {
            Text(
                text = product.title,
                style = MaterialTheme.typography.bodyLarge,
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, bottom = 16.dp, end = 16.dp)
                .verticalScroll(rememberScrollState()),
        ) {

            if (product.freeShipping) {
                Text(
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = "Envío gratis",
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = Green
                    ),
                )
            }
            Row(
                modifier = Modifier
                    .padding(bottom = 2.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Estado del producto: ${product.condition}",
                    modifier = Modifier.align(Alignment.Bottom),
                    style = TextStyle(
                        fontSize = 12.sp
                    )
                )

            }
            product.picturesUrl?.let { PicturesSlider(it) }
            product.price?.let {
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = it.formatPrice(product.currencyId),
                    style = TextStyle(
                        fontSize = 22.sp
                    )
                )
            }
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = "valid",//validateAvailableProducts(product.availableQuantity),
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Blue,
                    fontWeight = FontWeight.Light
                ),
            )
            Divider(modifier = Modifier.fillMaxWidth())
            Row(
                modifier = Modifier.padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = "Location Icon",
                    tint = Blue,
                )
                Text(
                    text = product.location ?: "Unknown",
                    style = TextStyle(
                        color = Blue,
                        fontWeight = FontWeight.Light,
                        fontSize = 16.sp,
                    ),
                )
            }
            Divider(modifier = Modifier.fillMaxWidth())
            Text(
                text = "Descripción",
                modifier = Modifier.padding(vertical = 8.dp),
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = product.title,
                style = MaterialTheme.typography.bodySmall

            )
        }
    }

}