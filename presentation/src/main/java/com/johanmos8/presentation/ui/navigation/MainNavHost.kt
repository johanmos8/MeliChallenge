package com.johanmos8.presentation.ui.navigation

import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.johanmos8.presentation.ui.screen.detail.DetailScreen
import com.johanmos8.presentation.ui.screen.home.HomeScreen
import com.johanmos8.presentation.ui.screen.home.HomeViewModel


@Composable
fun MainNavHost(
    homeViewModel: HomeViewModel,
    navController: NavHostController,
    modifier: Modifier
) {

    NavHost(
        navController = navController,
        startDestination = NavigationScreen.HomeScreen.route,
        modifier = modifier
    ) {
        composable(NavigationScreen.HomeScreen.route) {
            HomeScreen(homeViewModel = homeViewModel, navHostController = navController)
        }
        composable(
            NavigationScreen.DetailScreen.route,
            arguments = listOf(navArgument("itemId") {
                type = NavType.StringType
            })
        ) { it ->
            it.arguments?.getString("itemId")?.let { id ->
                DetailScreen(itemId = id, navHostController = navController)
            }

        }
    }
}