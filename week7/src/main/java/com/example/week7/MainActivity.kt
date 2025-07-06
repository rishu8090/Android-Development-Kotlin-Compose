package com.example.week7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.week7.day1_pagination.PaginationScreen
import com.example.week7.ui.theme.Intro_to_jetpack_composeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    //      lateinit var notificationReceiver: NotificationReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Intro_to_jetpack_composeTheme {
//               NetworkingScreen()
                PaginationScreen()
//                NotificationScreen()
            }
        }
//        notificationReceiver = NotificationReceiver()   // you must have to register your receiver before using it.
//        ContextCompat.registerReceiver(this,notificationReceiver, IntentFilter(NotificationUtility.MARK_AS_READ_RECEIVER_ACTION
//        ), ContextCompat.RECEIVER_NOT_EXPORTED)
//        NotificationUtility.createNotificationChannel(this)
    }

//    override fun onDestroy(){
//        unregisterReceiver(notificationReceiver)
//        super.onDestroy()
//    }
}


