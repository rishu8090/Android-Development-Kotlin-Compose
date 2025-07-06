package com.example.week3.day2

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

// if any composable contains any type of state then, it is known as stateful composable or if not known as stateless composable.

@Composable
fun ChildComposable(   // here, 2nd and 3rd parameters of function, is used for state hoisting and 2nd parameter for resolve read only problems.
                                    // 3rd parameter is used in state hoisting for resolve write only problems of ChildComposable.
    modifier: Modifier = Modifier,
    counter : Int,
    onCounterChanged : (Int) -> Unit
    ){
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Counter : $counter")   // here, read only problem arises.
        Button(
            onClick = {
//                counter++            // here, write problem arises.
                onCounterChanged(counter)   // here, onCounterChanged doesn't have the operation, it will only do that the operation that is given to function in form of parameter by parentComposable.
               // Log.d("TAG", "Counter : $counter")
            }
        ) {
            Text(text = "Increment counter")
        }
    }
}

@Composable
fun ThisFunctionDoesNotCareAboutState(counter: Int){  // this function saying , unlike childComposable which is reComposes everytime when counter changed
                                                     // but this function which is called by parentComposable is doesn't called all time,
    counter    // if function, reads the counter, then in only that condition, it will be recomposed. otherwise not.
    Log.d("TAG", "This_Function_Does_Not_Care_About_State")
}

@Composable  // this parentComposable for state Hoisting , used to change child counter by the parentComposable.
fun ParentComposable(){
    var counter : Int by remember { mutableStateOf(0) }
    ChildComposable(
        modifier = Modifier.fillMaxSize(),
        counter = counter,
        onCounterChanged = {
            counter += 2
        })
    ThisFunctionDoesNotCareAboutState(counter)
}

@Preview
@Composable
fun ParentComposablePreview(){
    ParentComposable()
}