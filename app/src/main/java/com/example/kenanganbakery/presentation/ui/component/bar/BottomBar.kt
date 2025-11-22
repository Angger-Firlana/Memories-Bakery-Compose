package com.example.kenanganbakery.presentation.ui.component.bar

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import com.example.kenanganbakery.data.local.BottomBarData
import com.example.kenanganbakery.data.local.UserManager
import com.example.kenanganbakery.domain.models.user.User

@Composable
fun BottomBar(
    navController: NavHostController,
    currentDestination: NavDestination?
) {
    val context = LocalContext.current
    val userManager = UserManager(context)

    val user = userManager.getUser() ?: User(id=0,username="Unknown", email = "Unknown", role_id = 0, role = null)

    var selectedIndex by remember { mutableStateOf(0) }
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.White
    ) {
        val bottomBarItems = if (user.role_id == 3) BottomBarData.listBottomBar else if (user.role_id == 2) BottomBarData.listBottomBarPetugas else TODO()
        bottomBarItems.forEachIndexed { index, item ->
            val selected = selectedIndex == index

            NavigationBarItem(
                selected = selected,
                onClick = {
                    selectedIndex = index
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
                        contentDescription = item.title,
                        tint = if (selected) Color.White else Color.Gray
                    )
                },
                label = {
                    Text(text = item.title, color = Color.White)
                }
            )
        }
    }
}
