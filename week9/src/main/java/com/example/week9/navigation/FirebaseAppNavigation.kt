package com.example.week9.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun FirebaseAppNavigation(modifier: Modifier = Modifier){
    val navController = rememberNavController()
    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = TopLevelDestination.Home.route
    )

}