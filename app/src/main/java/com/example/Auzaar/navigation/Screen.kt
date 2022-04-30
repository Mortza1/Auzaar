package com.example.Auzaar.navigation

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object HomeScreen : Screen("home_screen")
    object ProductDetailsScreen : Screen("product_details_screen")
    object AddToCartScreen : Screen("add_to_cart_screen")
    object LoginPage : Screen("login_page")
    object SignUpPage : Screen("signUp_page")

}
