package com.example.week9.navigation

import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.week9.splash.SplashScreen
import com.example.week9.authentication.signin.SignInScreen
import com.example.week9.authentication.signup.SignUpScreen
import com.example.week9.components.slideIntoContainerAnimation
import com.example.week9.components.slideOutOfContainerAnimation
import com.example.week9.home.HomeScreen

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    startDestination: NavigationDestination
) {

    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination.route
    ) {

        composable(
            route = NavigationDestination.SignIn.route,
            enterTransition = { slideIntoContainerAnimation() },
            exitTransition = { slideOutOfContainerAnimation() }
        ) {backStackEntry -> // internally it uses this to attach the view model to the composable.
            SignInScreen(
                authViewModel = hiltViewModel(backStackEntry), // if viewModel exist, same instance will be used. and if not new instance will be created.
                onSignUpClick = {
                    navController.navigate(NavigationDestination.SignUp.route)
                }
            )
        }



        composable(
            route = NavigationDestination.SignUp.route,
            enterTransition = { slideIntoContainerAnimation(towards = SlideDirection.Start) },
            exitTransition = { slideOutOfContainerAnimation(towards = SlideDirection.End) }
        ) {backStackEntry ->
            val parentEntry =
                remember(backStackEntry){navController.getBackStackEntry(NavigationDestination.SignIn.route)}
            SignUpScreen(
                authViewModel = hiltViewModel(parentEntry),   // in this way, hilt share common ViewModel between composable, if viewModel instance exist, same instance will be used. and if not new instance will be created.
                onBack = { navController.popBackStack() },
//                onSignUpSuccess = { navController.navigate(NavigationDestination.Home.route) }
            )
        }

        composable(
            route = NavigationDestination.Home.route,
//            enterTransition = { slideIntoContainerAnimation(towards = SlideDirection.Start) },
//            exitTransition = { slideOutOfContainerAnimation(towards = SlideDirection.End) }
        ) {
            HomeScreen()
        }

        composable(
            route = NavigationDestination.Splash.route,
            enterTransition = { slideIntoContainerAnimation() },
            exitTransition = { slideOutOfContainerAnimation() }
        ) {
            SplashScreen()
        }
    }
}

