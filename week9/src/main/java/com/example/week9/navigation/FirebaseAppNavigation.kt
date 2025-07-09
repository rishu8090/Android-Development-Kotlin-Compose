package com.example.week9.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.week9.authentication.signin.SignInScreen
import com.example.week9.authentication.signup.SignUpScreen
import com.example.week9.components.slideIntoContainerAnimation
import com.example.week9.components.slideOutOfContainerAnimation

@Composable
fun FirebaseAppNavigation(modifier: Modifier = Modifier) {

    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavigationDestination.SignIn.route
    ) {

        composable(
            route = NavigationDestination.SignIn.route,
            enterTransition = {slideIntoContainerAnimation()},
            exitTransition = {slideOutOfContainerAnimation()}
        ) {
            SignInScreen(
                onSignUpClick = {
                    navController.navigate(NavigationDestination.SignUp.route)
                }
            )
        }



        composable(
            route = NavigationDestination.SignUp.route,
            enterTransition = {slideIntoContainerAnimation()},
            exitTransition = {slideOutOfContainerAnimation()}
        ){
            SignUpScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }

}