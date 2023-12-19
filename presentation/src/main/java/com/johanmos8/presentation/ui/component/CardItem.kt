package com.johanmos8.presentation.ui.component

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.johanmos8.domain.model.ItemDetail
import com.johanmos8.presentation.util.convertToHttps
import com.johanmos8.presentation.util.formatPrice
import com.johanmos8.presentation.util.getAsyncImageModel

@Composable
fun CardItem(
    modifier: Modifier = Modifier,
    item: ItemDetail,
    onItemClick: (String) -> Unit,
) {
    Card(
        modifier = modifier

            .fillMaxSize()

            .padding(horizontal = 16.dp, vertical = 4.dp),
        shape = RoundedCornerShape(CornerSize(16.dp)),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Column(
            modifier = Modifier
                .clickable {
                    Log.d("CardItem click", "CardItem: ${item.id}")
                    onItemClick(item.id)
                }
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                AsyncImage(
                    model = getAsyncImageModel(
                        data = item.thumbnail?.convertToHttps(),
                        context = LocalContext.current
                    ),
                    contentDescription = null,
                    modifier = modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape),
                    contentScale = ContentScale.Crop
                )


                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 12.dp, vertical = 4.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.labelMedium
                    )
                    item.price?.formatPrice(item.currencyId)?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.outline
                        )
                    }
                }

            }
        }
    }
}
