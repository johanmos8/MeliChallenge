package com.johanmos8.presentation.ui.component

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.johanmos8.presentation.R

@Composable
fun ErrorMessage(
    modifier: Modifier = Modifier,
    errorMessage: String?,
    onRetry: () -> Unit
) {


    Box(modifier = Modifier.fillMaxSize()) {


        if (errorMessage != null) {
            Snackbar(
                modifier = Modifier
                    .padding(all = 8.dp)
                    .align(alignment = Alignment.BottomCenter), // show the snackbar at the bottom of the screen
                action = {
                    TextButton(
                        onClick = {
                            onRetry()
                        }
                    ) {
                        Text(text = stringResource(id = com.johanmos8.presentation.R.string.retry))
                    }
                }
            ) {
                Text(text = errorMessage)
            }
        }
    }
}
