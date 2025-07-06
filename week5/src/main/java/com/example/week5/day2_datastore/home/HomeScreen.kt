package com.example.week5.day2_datastore.home

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.week5.R
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "HomeScreen") },
                actions = {
                    IconButton(onClick = {homeViewModel.logout()}) {
                        Icon(
                            painter = painterResource(R.drawable.ic_logout),
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
            Text(text = "HomeScreen")
            Button(onClick = {
                homeViewModel.logout()
            }
            ) {
                Text(text = "Logout")
            }
        }
    }
}