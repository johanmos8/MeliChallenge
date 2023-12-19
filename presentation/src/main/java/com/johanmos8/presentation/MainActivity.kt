package com.johanmos8.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.johanmos8.presentation.ui.navigation.MainNavHost
import com.johanmos8.presentation.ui.screen.home.HomeScreen
import com.johanmos8.presentation.ui.screen.home.HomeViewModel
import com.johanmos8.presentation.ui.theme.MeliChallengeTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val homeViewModel: HomeViewModel by viewModels()
        setContent {
            MeliChallengeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    MainNavHost(
                        homeViewModel = homeViewModel,
                        navController = navController,
                        modifier = Modifier.padding(0.dp)
                    )

                }
            }
        }
    }
}
