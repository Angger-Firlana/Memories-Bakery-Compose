package com.example.kenanganbakery.data.local

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PersonPin
import com.example.kenanganbakery.domain.models.bottombar.BottomBarItem
import com.example.kenanganbakery.presentation.navigation.Screen

object BottomBarData{
    val listBottomBar = listOf(
        BottomBarItem(
            title= "Dashboard",
            route = Screen.Dashboard.route,
            icon = Icons.Default.Home
        ),
        BottomBarItem(
            title= "Menu",
            route = Screen.Menu.route,
            icon = Icons.Default.MenuBook
        ),
        BottomBarItem(
            title= "History",
            route = Screen.History.route,
            icon = Icons.Default.History
        ),
        BottomBarItem(
            title= "Profile",
            route = Screen.Profile.route,
            icon = Icons.Default.PersonPin
        )
    )
}