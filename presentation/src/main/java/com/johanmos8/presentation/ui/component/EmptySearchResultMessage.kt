package com.johanmos8.presentation.ui.component


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.johanmos8.presentation.R

@Composable
fun EmptySearchResultMessage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background)
            .fillMaxHeight()
            .wrapContentHeight(Alignment.CenterVertically) // Centra verticalmente
    ) {
        Text(
            text = stringResource(R.string.items_not_found),
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
        )
    }
}
