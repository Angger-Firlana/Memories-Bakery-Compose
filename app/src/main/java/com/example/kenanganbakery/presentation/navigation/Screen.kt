package com.example.kenanganbakery.presentation.navigation

sealed class Screen(val route:String) {
    data object Welcome:Screen("welcome")
    data object Auth:Screen("login")
    data object Dashboard:Screen("dashboard")
    data object Menu:Screen("menu")
    data object History:Screen("history")

    data object DashboardPetugas:Screen("dashboard_petugas")
    data object HistoryPetugas:Screen("history_petugas")
    data object Profile:Screen("profile")
    data object CheckoutScreen:Screen("checkout")

    data object DashboardKurir:Screen("dashboard_kurir")
    data object HistoryKurir:Screen("history_kurir")
}