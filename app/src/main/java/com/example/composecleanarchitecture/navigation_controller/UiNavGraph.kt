package com.example.composecleanarchitecture.navigation_controller

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composecleanarchitecture.ui.features.login.LoginScreen
import com.example.composecleanarchitecture.ui.features.splash.SplashScreen

@Composable
fun UiNavGraph( ) {
    val navBarNavController = rememberNavController()
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = "SplashScreen") {
        composable(Destinations.SplashScreen.route) { SplashScreen(navController) }
        composable(Destinations.LoginScreen.route) { LoginScreen(navController) }
        composable(Destinations.DashBoardScreen.route) {DashBoardScreen(navBarNavController){
            navController.navigate(Destinations.LoginScreen.route)
        } }
    }
}