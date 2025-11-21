package com.example.kenanganbakery.presentation.ui.component.bar

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import com.example.kenanganbakery.data.local.BottomBarData

@Composable
fun BottomBar(
    navController: NavHostController,
    currentDestination: NavDestination?
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onBackground
    ) {
        BottomBarData.listBottomBar.forEach { item ->
            val selected = currentDestination?.hierarchy?.any {
                it.route == item.route
            } == true

            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(text = item.title)
                }
            )
        }
    }
}
