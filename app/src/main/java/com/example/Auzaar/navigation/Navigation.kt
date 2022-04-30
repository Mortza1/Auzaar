package com.example.Auzaar.navigation

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.Auzaar.ui.theme.AuzaarTheme
import com.example.Auzaar.view.AddToCartScreen
import com.example.Auzaar.view.Dashboard
import com.example.Auzaar.view.ProductDetailsScreen
import com.example.Auzaar.view.homeScreen.HomeScreenViewModel
import com.example.Auzaar.view.splashScreen.SplashScreen
import com.example.Auzaar.view.login.LoginPage
import com.example.Auzaar.view.login.loginViewModel
import com.example.Auzaar.view.signUp.SignUpPage
import com.example.Auzaar.view.signUp.signUpViewModel
import com.example.Auzaar.view.splashScreen.SplashScreenViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(Screen.SplashScreen.route) {
            AuzaarTheme {
                 Surface(color = MaterialTheme.colors.primary){
            SplashScreen(navController = navController, viewModel = SplashScreenViewModel())}}
        }
        composable(Screen.HomeScreen.route) {
            Dashboard(navController = navController)
        }
        composable(Screen.ProductDetailsScreen.route) {
            ProductDetailsScreen()
        }
        composable(Screen.AddToCartScreen.route) {
            AddToCartScreen(navDrawerViewModel = HomeScreenViewModel())
        }
        composable(Screen.LoginPage.route){
            LoginPage(navController = navController, viewModel = loginViewModel())
        }
        composable(Screen.SignUpPage.route){
            SignUpPage(navController = navController, viewModel = signUpViewModel())
        }

    }
}