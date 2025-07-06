package com.example.week3.day2

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

// in here, due to composition, (without remember ) value is incremented 0 to 1. but later it is forget by the compiler,
// but after remember keyword the, we does'nt forget the value and reassign it to the counter. in that way recomposition is worked.

@Composable
fun CompositionExample(){
//    var counter = 0    // this is used when without composition and recomposition.
//     val counter  = remember {         // this is 1st type of use of mutableState (Regular method)
//          Log.d("TAG", "This will be printed only once.")
//         mutableStateOf(0) }  // this val is used when you want compo and recomposition in the code
//                                            // when you want to compose update the UI when some change in val(state) is happened.
//    Log.d("TAG","This will be printed on every composition")
//
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text("Counter: = ${counter.value}")   // for var, counter is used in place of counter.value.
//
//        Button(
//            onClick = {
//                counter.value++       // for var, counter++ is used .
//                Log.d("TAG", "Counter : ${counter.value}")  // for var, counter is used in place of counter.value.
//            }
//        ) {
//            Text("Click Button to increment counter")
//        }
//    }


    // Composition by delegation.   // In other method you have to treat counter as a state, but in delegation method you only have to take counter as a Integer.

         var counter: Int by remember { mutableStateOf(0) }  // this val is used when you want compo and recomposition in the code
                                            // when you want to compose update the UI when some change in val(state) is happened.
    Log.d("TAG","This will be printed on every composition")

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Counter: = ${counter}")   // for var, counter is used in place of counter.value.

        Button(
            onClick = {
                counter++       // for var, counter++ is used .
                Log.d("TAG", "Counter : ${counter}")  // for var, counter is used in place of counter.value.
            }
        ) {
            Text("Click Button to increment counter")
        }
    }
}


@Composable
fun TextFieldExample(){     // in this way you create a functional editText in compose.
  //  var text by remember { mutableStateOf("") }    /// it is for 2nd type of mutableState ( by using property delegation).
    var (text, onTextChanged) = remember { mutableStateOf("") }  // this is 3rd type of mutableState( here, we you de-structuring).

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = text,
//            onValueChange = {          // this commented part for 2nd type of mutableState
//                updatedVaule -> text = updatedVaule
//                Log.d("TAG", "UpdatedValue : $updatedVaule") }
            onValueChange = onTextChanged    // this is for 3rd type of mutableState.
        )
    }
}

@Preview
@Composable
fun CompositionExamplePreview(){
    CompositionExample()
}

@Preview
@Composable
fun TextFieldExamplePreview(){
    TextFieldExample()
}