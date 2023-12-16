package com.johanmos8.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.johanmos8.presentation.ui.screen.home.HomeScreen


@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = NavigationScreen.HomeScreen.route,
        modifier = modifier
    ) {
        composable(NavigationScreen.HomeScreen.route) {
            HomeScreen()
        }
    }
}