package com.johanmos8.presentation.ui.component


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PicturesSlider(images: List<String>) {


    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        ImageCarousel(images = images)
        Spacer(modifier = Modifier.padding(4.dp))

    }
}


@Composable
fun ImageCarousel(images: List<String>) {
    val scrollState = rememberLazyListState()

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        state = scrollState
    ) {
        items(images) { image ->
            AsyncImage(
                model = image,
                contentDescription = "Product Image",
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .height(300.dp)
            )
        }
    }
}