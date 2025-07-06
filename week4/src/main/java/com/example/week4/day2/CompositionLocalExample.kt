package com.example.week4.day2

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.week4.ui.theme.Intro_to_jetpack_composeTheme

@Composable
fun DefaultCompositionLocalExample(){
    CompositionLocalProvider(// this function is used to supply different colors to the child function of this function. this will help to change color without going explicitly.
        LocalContentColor provides Color.Blue,
        LocalTextStyle provides MaterialTheme.typography.headlineMedium

    ) {
        Text(text = "DefaultCompositionLocalExample")
    }

}

//@Preview
//@Composable
//fun CompositionLocalExamplePreview(){
//    Intro_to_jetpack_composeTheme {
//        DefaultCompositionLocalExample()
//    }
//}

    data class MyColor ( val value: Color)
    val localMyColor = compositionLocalOf { MyColor(value = Color.Green) }

@Composable
fun CustomCompositionLocalExample(){    // Compose team say avoid to use such things, like in here, in here value will be passed to all child of a parent
    val myColor = localMyColor.current   /// and all child to child , this will cost to unused space in disc. this will reduce the performance.
    Column{
        Text(
        text = "CustomCompositionLocalExample 1",
        color = myColor.value
    )

        CompositionLocalProvider(
            localMyColor provides MyColor(value = Color.Green)
        ) {
            val myNewColor = localMyColor.current
            Text(
                text = "CustomCompositionLocalExample 1",
                color = myNewColor.value
            )
        }
    }
}

@Preview
@Composable
fun CustomCompositionLocalExamplePreview(){
    CompositionLocalProvider(
        localMyColor provides MyColor(value = Color.Red)
        ) {
    CustomCompositionLocalExample()
    }
}