package com.example.week4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.week4.day3_hilt.DIViewModel
import com.example.week4.ui.theme.Intro_to_jetpack_composeTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
     // Field _Injection in Activity, bcz we can't pass object as parameter in activity that's why we doesn't use constructor injection.
   // @Inject
//    lateinit var  diViewModel :  DIViewModel  // late init is used to avoid null pointer exception. // bcz  we are not assigning value right now.and we let hilt create a instance.
    val diViewModel by viewModels<DIViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Intro_to_jetpack_composeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
        diViewModel.doSomething()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Intro_to_jetpack_composeTheme {
        Greeting("Android")
    }
}