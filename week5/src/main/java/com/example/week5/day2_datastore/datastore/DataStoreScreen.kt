package com.example.week5.day2_datastore.datastore

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.week5.day2_datastore.home.HomeScreen
import com.example.week5.day2_datastore.login.LoginScreen

@Composable
fun DataStoreScreen(
    dataStoreViewModel: DataStoreViewModel = hiltViewModel()
) {
        val authenticated by dataStoreViewModel.authenticated.collectAsState()  // collectAsState() is used to convert a Stateflow to a state bcz compose only understand state.
    if(authenticated){
        HomeScreen()
    }else{
        LoginScreen()
    }

}
