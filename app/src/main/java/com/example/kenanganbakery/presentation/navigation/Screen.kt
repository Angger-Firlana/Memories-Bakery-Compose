package com.example.kenanganbakery.presentation.navigation

sealed class Screen(val route:String) {
    data object Welcome:Screen("welcome")
    data object Auth:Screen("login")
    data object Dashboard:Screen("dashboard")
    data object Menu:Screen("menu")
    data object History:Screen("history")
    data object Profile:Screen("profile")
}