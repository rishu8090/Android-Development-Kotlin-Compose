package com.example.week9

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.week9.authentication.signup.SignUpScreen
import com.example.week9.navigation.FirebaseAppNavigation
import com.example.week9.ui.theme.Intro_to_jetpack_composeTheme

@Composable
fun FirebaseApp(modifier: Modifier = Modifier){
    Intro_to_jetpack_composeTheme {
        FirebaseAppNavigation()
    }
}