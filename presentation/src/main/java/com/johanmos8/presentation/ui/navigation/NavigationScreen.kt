package com.johanmos8.presentation.ui.navigation

sealed class NavigationScreen(val route: String) {
    object HomeScreen : NavigationScreen("home")
    object DetailScreen : NavigationScreen("detail")
}
