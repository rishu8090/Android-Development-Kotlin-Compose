package com.example.week7.day2_notifications

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun NotificationScreen(){
    val context = LocalContext.current
    val notificationPermission = rememberPermissionState(android.Manifest.permission.POST_NOTIFICATIONS)

    LaunchedEffect(Unit) {
        notificationPermission.launchPermissionRequest()
    }
    Scaffold(
        topBar ={
        TopAppBar(title = { Text(text = "NotificationScreen") })
        }
    ) { innerPadding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                NotificationUtility.createSimpleNotification(context)
            }) {
                Text(text = "Display a simple notification")
            }

            Button(onClick = {
                NotificationUtility.createClickableNotification(context)
            }) {
                Text(text = "Display a clickable notification")
            }

            Button(onClick = {
                NotificationUtility.createActionableNotification(context)
            }) {
                Text(text = "Display a actionable notification")
            }
        }
    }
}