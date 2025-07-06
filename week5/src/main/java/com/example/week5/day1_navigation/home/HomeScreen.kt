package com.example.week5.day1_navigation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen (
    // navController: NavController   // we want to avoid navigational computation in their home or detailed screen. that why we us call back onDetailsClicked
    onDetailsClicked: (String) -> Unit  // with help of this call back to navigate to detail screen which is a good practice to avoid navigational computation, all work is done in AppNavigation.kt.
){       // to pass some data on click of button we pass string as parameter in onDetailsClicked.
    var text by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
         TopAppBar(
             title = { Text(text = "HomeScreen") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Home Screen")
             TextField(
                 value = text,
                 onValueChange = {text = it}
             )
            Button(onClick = {
               // navController.navigate(com.example.week5.day1_navigation.NavDestination.Details.name)
                onDetailsClicked(text)
            }) {
                Text(text = "Goto Details Screen")
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview(){
   // HomeScreen(rememberNavController())  // you can also use this but this is not a good method to view preview.  // in general, we avoid to use navController.
    HomeScreen(onDetailsClicked = { })
}