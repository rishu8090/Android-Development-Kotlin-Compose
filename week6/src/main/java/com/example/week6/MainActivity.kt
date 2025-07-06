package com.example.week6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.week6.day1_day2_room.DatabaseScreen
import com.example.week6.day4_retrofit.NetworkingScreen
import com.example.week6.ui.theme.Intro_to_jetpack_composeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Intro_to_jetpack_composeTheme {
//                DatabaseScreen() // User -> UserDao -> UserDatabase -> UserRepository -> ViewModel -> View(UserScreen)   this is the flow.
//                FileStorageScreen()
                NetworkingScreen()
            }
        }
    }
}
