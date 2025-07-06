package com.example.week4.day2

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AnimationExample(

){
    var showBox by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxSize(),
//            .animateContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){

//        if(showBox) { }
        AnimatedVisibility(
            visible = showBox,
            enter = fadeIn(animationSpec = tween(1500)) + expandHorizontally(animationSpec = tween(1500)),
            exit = fadeOut(animationSpec = tween(1500)) + shrinkVertically(animationSpec = tween(1500))) { // this function will show inner code only if parameter is true else not.
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(18.dp))
                    .size(100.dp)
                    .background(Color.Cyan)
            )
        }

        Button(onClick = {
            showBox = !showBox
        }) {
                Text("Toggle Box Visibility")
        }
    }
}

@Preview
@Composable
fun AnimationExamplePreview(){
    AnimationExample()
}

@Composable
fun AnimateColorAsStateExample(){

//    var boxColor by remember{ mutableStateOf(Color.Blue) }
    var changeColor by remember { mutableStateOf(true) }
    val boxColor by animateColorAsState(targetValue = if(changeColor) Color.Blue else Color.Yellow,
        animationSpec = tween(2000))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .animateContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(18.dp))
                .size(100.dp)
                .background(boxColor)
        )

    Button(
        onClick = {
//           boxColor = if(boxColor == Color.Blue) Color.Yellow else  Color.Blue  // this method will create a immediate change of color.

//            boxColor = if(boxColor == Color.Blue) Color.Yellow
//            else if(boxColor == Color.Yellow) Color.Red
//            else if(boxColor == Color.Red) Color.Green
//            else Color.Blue

            changeColor = !changeColor  // this will create a smooth change of color.
        }) {
        Text("Toggle Box Color")
         }
    }
}

@Preview
@Composable
fun AnimateXColorAsStateExamplePreview(){
    AnimateColorAsStateExample()
}

@Composable
fun AnimateFloatAsStateExample(){

//    var boxColor by remember{ mutableStateOf(Color.Blue) }
    var progress by remember { mutableFloatStateOf(0f) }
    val scope = rememberCoroutineScope()   // CoroutineScope is used here, to use delay() method.
//    val boxColor by animateFloatAsState(targetValue = progress)

    LaunchedEffect(Unit) {   // you can use LaunchedEffect to run the inner operations without clicking any button.
        scope.launch {
                    while(progress <= 1f){
                    progress += 0.01f
                    delay(250)
                    }
                }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .animateContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
         LinearProgressIndicator(progress = {progress})  // it creates a linear progress bar.
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
//                scope.launch {
//                    while(progress <= 1f){
//                    progress += 0.01f
//                    delay(250)
//                    }
//                }
            }
        ) {
            Text("Start Progress")
        }
    }
}

@Preview
@Composable
fun AnimateFloatAsStateExamplePreview() {
    AnimateFloatAsStateExample()
}

@Composable
fun InfiniteTransition(){
    val scope = rememberCoroutineScope()
    var changeColor by remember { mutableStateOf(true) }
    val boxColor by animateColorAsState(
        targetValue = if(changeColor) Color.Blue else Color.Yellow,
       // animationSpec = tween(2000)
    )
    LaunchedEffect(Unit) {
        scope.launch {
        while (true){
            changeColor = !changeColor
            delay(200)
            }
        }
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(boxColor)) {  }

}

@Preview
@Composable
fun InfiniteTransitionPreview(){
    InfiniteTransition()
}
