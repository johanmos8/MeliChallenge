package com.johanmos8.presentation.ui.navigation

sealed class NavigationScreen(val route: String) {
    object HomeScreen : NavigationScreen("home")
    object DetailScreen : NavigationScreen("detail/{itemId}") {
        fun createRoute(itemId: String): String = "detail/$itemId"
        const val ARG_ITEM_ID = "itemId"
    }
}
