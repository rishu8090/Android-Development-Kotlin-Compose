package com.example.week5.day2_datastore.login

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel<LoginViewModel>()  // you can use of this can call a viewModel instance by the help of hilt navigation compose build directory.
){
    Scaffold(
        topBar = {
         TopAppBar(title = { Text(text = "LoginScreen") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "LoginScreen")
            Button(onClick = {
        loginViewModel.login()
            }) {
                Text(text = "Login")
            }
        }
    }
}