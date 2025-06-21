package com.phonepesecure.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.phonepesecure.ui.AlertScreen
import com.phonepesecure.ui.HomeScreen
import com.phonepesecure.ui.SplashScreen
import com.phonepesecure.ui.spend.SpendChartScreen
import com.phonepesecure.viewmodel.ThemeViewModel

@Composable
fun AppNavigation(themeViewModel: ThemeViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash" // Start from Splash Screen
    ) {
        composable("splash") {
            SplashScreen(navController = navController)
        }
        composable("home") {
            HomeScreen(navController = navController, themeViewModel = themeViewModel)
        }
        composable("alerts") {
            AlertScreen(navController = navController)
        }
        composable("spend_chart") {
            SpendChartScreen(navController = navController)
        }
    }
}
