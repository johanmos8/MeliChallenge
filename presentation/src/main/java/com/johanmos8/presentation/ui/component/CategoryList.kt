package com.johanmos8.presentation.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.johanmos8.domain.model.ItemCategory

@Composable
fun CategoryList(modifier: Modifier,categories: List<ItemCategory>, onCategoryClicked: (ItemCategory) -> Unit) {
    LazyColumn(modifier = modifier) {
        items(categories) { category ->
            CategoryItem(category = category, onCategoryClicked = onCategoryClicked)
        }
    }
}

@Composable
fun CategoryItem(category: ItemCategory, onCategoryClicked: (ItemCategory) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onCategoryClicked(category) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = category.name,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(4.dp))

        }
    }
}

