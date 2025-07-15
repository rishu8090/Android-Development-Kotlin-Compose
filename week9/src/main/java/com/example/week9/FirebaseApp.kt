package com.example.week9

import androidx.compose.runtime.Composable
import com.example.week9.navigation.AppNavigation
import com.example.week9.navigation.NavigationDestination
import com.example.week9.ui.theme.Intro_to_jetpack_composeTheme

@Composable
fun FirebaseApp(
    startDestination: NavigationDestination
) {
    Intro_to_jetpack_composeTheme {
        AppNavigation(
            startDestination = startDestination
        )
    }
}