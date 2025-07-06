package com.example.week5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.week5.day2_datastore.datastore.DataStoreScreen
import com.example.week5.ui.theme.Intro_to_jetpack_composeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Intro_to_jetpack_composeTheme {
               //  AppNavigation()
                DataStoreScreen()  // it is similar to shared_preference like in java
            }
        }
    }
}

