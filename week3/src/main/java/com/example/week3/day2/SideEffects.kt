package com.example.week3.day2

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun EffectExample(
   modifier: Modifier = Modifier
){
    var counter1 : Int by remember { mutableIntStateOf(0) }
    var counter2 : Int by remember { mutableIntStateOf(0) }

    LaunchedEffect(key1 = counter2) {  // and if 2 or more more key present, then it will follow OR operations.
        Log.d("TAG", "This will be printed on only Counter2 changes")
    }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Counter1 : $counter1")
        Text("Counter2 : $counter2")

//        Log.d("TAG", "This will be printed when any of the counter states changes")  // here, this will be printed all times when any of the counter 1or 2 changes. which is a side effect.
        Button(
            onClick = {
                counter1++
                if(counter1 % 2 == 0) counter2++
            }
        ) {
            Text(text = "Increment Operator")
        }
    }
}

@Preview
@Composable
private fun EffectExamplePreview(){
    EffectExample(modifier =Modifier.fillMaxSize())
}