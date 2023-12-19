package com.johanmos8.presentation.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.johanmos8.presentation.ui.screen.home.HomeViewModel

@Composable
@Preview
fun LoadingIndicator(
   isLoading: Boolean = false
) {

    if (isLoading) {
        Surface(modifier = Modifier.fillMaxSize(), color = Color.Transparent) {
            CircularProgressIndicator(
                modifier = Modifier.wrapContentSize(Alignment.Center),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}