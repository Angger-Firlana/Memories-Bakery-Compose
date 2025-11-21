package com.example.kenanganbakery.presentation.navigation

import android.app.Application
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kenanganbakery.data.local.TokenManager
import com.example.kenanganbakery.data.local.WelcomeManager
import com.example.kenanganbakery.presentation.ui.component.bar.BottomBar
import com.example.kenanganbakery.presentation.ui.screen.auth.AuthScreen
import com.example.kenanganbakery.presentation.ui.screen.pelanggan.dashboard.DashboardScreen
import com.example.kenanganbakery.presentation.ui.screen.pelanggan.history.HistoryScreen
import com.example.kenanganbakery.presentation.ui.screen.pelanggan.menu.MenuScreen
import com.example.kenanganbakery.presentation.ui.screen.profile.ProfileScreen
import com.example.kenanganbakery.presentation.ui.screen.splash.welcome.WelcomeScreen
import com.example.kenanganbakery.presentation.viewmodel.AuthViewModel
import com.example.kenanganbakery.presentation.viewmodel.BranchViewModel
import com.example.kenanganbakery.presentation.viewmodel.MenuViewModel
import com.example.kenanganbakery.presentation.viewmodel.OrderViewModel
import com.example.kenanganbakery.presentation.viewmodel.TypeViewModel

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current
    val tokenManager = TokenManager(context)
    val welcomeManager = WelcomeManager(context)
    val authViewModel: AuthViewModel = viewModel(
        factory = ViewModelProvider.AndroidViewModelFactory.getInstance(application = context.applicationContext as Application)
    )

    val orderViewModel:OrderViewModel = viewModel(
        factory = ViewModelProvider.AndroidViewModelFactory.getInstance(application = context.applicationContext as Application)
    )

    val branchViewModel:BranchViewModel = viewModel(
        factory = ViewModelProvider.AndroidViewModelFactory.getInstance(application = context.applicationContext as Application)
    )

    val menuViewModel: MenuViewModel = viewModel(
        factory = ViewModelProvider.AndroidViewModelFactory.getInstance(application = context.applicationContext as Application)
    )

    val typeViewModel:TypeViewModel = viewModel(
        factory = ViewModelProvider.AndroidViewModelFactory.getInstance(application = context.applicationContext as Application)
    )

    var showBottomBar by remember {mutableStateOf(false )}
    val checkStartLogin = if(tokenManager.getToken() != null) Screen.Dashboard.route else Screen.Auth.route
    val startDestination = if (welcomeManager.getStateWelcome()) Screen.Welcome.route else checkStartLogin
    Scaffold(
        bottomBar = {
            if (showBottomBar){
                val navBackStackEntry = navController.currentBackStackEntry
                val currentDestination = navBackStackEntry?.destination

                // tampilkan bottom bar hanya di 4 screen utama
                if (currentDestination?.route in listOf(
                        Screen.Dashboard.route,
                        Screen.Menu.route,
                        Screen.History.route,
                        Screen.Profile.route
                    )
                ) {
                    BottomBar(
                        navController = navController,
                        currentDestination = currentDestination
                    )
                }
            }

        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Menu.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Auth.route) {
                showBottomBar = false
                AuthScreen(
                    viewModel = authViewModel,
                    onLogin = {
                        navController.navigate(Screen.Dashboard.route){
                            popUpTo(navController.graph.startDestinationId){inclusive = true}
                        }
                    }
                )
            }

            composable(Screen.Welcome.route) {
                showBottomBar = false
                WelcomeScreen() {
                    welcomeManager.setStateWelcome(false)
                    navController.navigate(Screen.Auth.route){
                        popUpTo(Screen.Welcome.route){inclusive = true}
                    }
                }
            }

            composable(Screen.Dashboard.route) {
                showBottomBar = true
                DashboardScreen()
            }

            composable(Screen.Menu.route) {
                showBottomBar = true
                MenuScreen(
                    branchViewModel = branchViewModel,
                    menuViewModel = menuViewModel,
                    orderViewModel = orderViewModel,
                    typeViewModel = typeViewModel
                )
            }

            composable(Screen.History.route) {
                showBottomBar = true
                HistoryScreen()
            }

            composable(Screen.Profile.route){
                showBottomBar = true
                ProfileScreen()
            }

        }
    }

}