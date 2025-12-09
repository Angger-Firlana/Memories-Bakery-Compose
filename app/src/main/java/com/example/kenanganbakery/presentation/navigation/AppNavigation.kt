package com.example.kenanganbakery.presentation.navigation

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
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
import com.example.kenanganbakery.data.local.UserManager
import com.example.kenanganbakery.data.local.WelcomeManager
import com.example.kenanganbakery.domain.models.user.User
import com.example.kenanganbakery.presentation.ui.component.bar.BottomBar
import com.example.kenanganbakery.presentation.ui.screen.auth.AuthScreen
import com.example.kenanganbakery.presentation.ui.screen.pelanggan.checkout.BakeryCheckoutScreen
import com.example.kenanganbakery.presentation.ui.screen.pelanggan.dashboard.DashboardScreen
import com.example.kenanganbakery.presentation.ui.screen.pelanggan.history.HistoryScreen
import com.example.kenanganbakery.presentation.ui.screen.pelanggan.menu.MenuScreen
import com.example.kenanganbakery.presentation.ui.screen.petugas.dashboard.DashboardPetugasScreen
import com.example.kenanganbakery.presentation.ui.screen.petugas.history.HistoryPetugasScreen
import com.example.kenanganbakery.presentation.ui.screen.profile.ProfileScreen
import com.example.kenanganbakery.presentation.ui.screen.splash.welcome.WelcomeScreen
import com.example.kenanganbakery.presentation.viewmodel.AuthViewModel
import com.example.kenanganbakery.presentation.viewmodel.BranchViewModel
import com.example.kenanganbakery.presentation.viewmodel.MenuViewModel
import com.example.kenanganbakery.presentation.viewmodel.OrderViewModel
import com.example.kenanganbakery.presentation.viewmodel.ProductionScheduleViewModel
import com.example.kenanganbakery.presentation.viewmodel.TypeViewModel

@RequiresApi(Build.VERSION_CODES.BAKLAVA)
@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current
    val userManager = UserManager(context)
    val tokenManager = TokenManager(context)
    val welcomeManager = WelcomeManager(context)

    val role = userManager.getUser()?.role

    val authViewModel: AuthViewModel = viewModel(
        factory = ViewModelProvider.AndroidViewModelFactory.getInstance(application = context.applicationContext as Application)
    )

    val orderViewModel:OrderViewModel = viewModel(
        factory = ViewModelProvider.AndroidViewModelFactory.getInstance(application = context.applicationContext as Application)
    )

    val productionScheduleViewModel:ProductionScheduleViewModel = viewModel(
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
    val checkStartLogin = if(tokenManager.getToken() != null){
        if (role?.id == 3){
            Screen.Dashboard.route

        }else if (role?.id == 2){
            Screen.DashboardPetugas.route
        } else {
            Screen.Dashboard.route
        }
    } else {
        Screen.Auth.route
    }
    val startDestination = if (welcomeManager.getStateWelcome()) Screen.Welcome.route else checkStartLogin
    Scaffold(
        bottomBar = {
            if (showBottomBar){

                val navBackStackEntry = navController.currentBackStackEntry
                val currentDestination = navBackStackEntry?.destination

                // tampilkan bottom bar hanya di 4 screen utama

                BottomBar(
                    navController = navController,
                    currentDestination = currentDestination
                )

            }

        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Auth.route) {
                showBottomBar = false
                AuthScreen(
                    viewModel = authViewModel,
                    onLogin = {
                        if (role?.id == 3){
                            navController.navigate(Screen.Dashboard.route){
                                popUpTo(navController.graph.startDestinationId){inclusive = true}
                            }
                        }else{
                            navController.navigate(Screen.DashboardPetugas.route){
                                popUpTo(navController.graph.startDestinationId){inclusive = true}
                            }
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

            composable(Screen.HistoryPetugas.route){
                showBottomBar = true
                HistoryPetugasScreen(
                    productionScheduleViewModel = productionScheduleViewModel
                )
            }

            composable(Screen.Menu.route) {
                showBottomBar = true
                MenuScreen(
                    branchViewModel = branchViewModel,
                    menuViewModel = menuViewModel,
                    orderViewModel = orderViewModel,
                    navController = navController,
                    typeViewModel = typeViewModel
                )
            }

            composable(Screen.CheckoutScreen.route){
                showBottomBar = false
                BakeryCheckoutScreen()
            }

            composable(Screen.DashboardPetugas.route) {
                showBottomBar = true
                DashboardPetugasScreen(
                    productionScheduleViewModel = productionScheduleViewModel
                )
            }

            composable(Screen.History.route) {
                showBottomBar = true
                HistoryScreen(
                    orderViewModel
                )
            }

            composable(Screen.Profile.route){
                showBottomBar = true
                ProfileScreen(
                    backToLogin = {
                        navController.navigate(Screen.Auth.route){
                            popUpTo(navController.graph.startDestinationId){inclusive = true}
                        }
                    }
                )
            }

        }
    }

}