package com.example.week5.day1_navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.week5.day1_navigation.detail.DetailScreen
import com.example.week5.day1_navigation.home.HomeScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavDestination.Home.name   // it decides which screen to show first
    ){
        composable(  // this composable fun is used as navGraph.
            route = NavDestination.Home.name
        ){
            HomeScreen(
                onDetailsClicked = {text ->
                    val route = buildString {   // in that way, you can use defaultValue in navArgument.
                       append(NavDestination.Details.name)
                        if(text.isNotBlank()){
                            append("?userId=$text")
                        }
                    }
//                    navController.navigate(NavDestination.Details.name + "/$text")
                    navController.navigate(route)
                }
            )
        }

        composable(
//            route = NavDestination.Details.name + "/{userId}",   // data  passing from home screen to detail screen , by using /{userId}
            route = NavDestination.Details.name + "?userId={userId}",
            arguments = listOf(
                navArgument("userId"){
                    type = NavType.StringType
                    defaultValue = "Default UserName"
                }
            )
        ){ backstackEntry ->
            val userId = backstackEntry.arguments?.getString("userId") ?: return@composable        // here we retrieve a string.   // bundle passing.
            DetailScreen( userId = userId,                        // ?: elvish operator if userId is null then return@composable and show error and don't go to the further code.
            onBackClicked = {navController.popBackStack() })
        }
    }
}