package com.example.week9.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.week9.R
import com.example.week9.settings.SettingsViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(
    settingsViewModel: SettingsViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel,
    onDetailClick: () -> Unit
) {

    val notificationPermission =
        rememberPermissionState(android.Manifest.permission.POST_NOTIFICATIONS)

    LaunchedEffect(Unit) {
        notificationPermission.launchPermissionRequest()
    }

    var nullableProperty: Int? = null
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "HomeScreen") },
                actions = {
                    IconButton(onClick = {
                        settingsViewModel.logout()
                    }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_logout),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Home")

            Button(onClick = onDetailClick) {
                Text("Go to Detail Screen!")
            }

            Button(onClick = {
                nullableProperty!!.inc()
            }) {
                Text(text = "Crash the App")
            }
        }
    }
}