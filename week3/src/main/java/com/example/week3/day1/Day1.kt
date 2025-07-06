package com.example.week3.day1

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.intro_to_jetpack_compose.R

@Composable   // functions which invoke @Composable functions must be marked with the @Composable annotation
fun MyFirstComposable(){ // by default all layout items are overlaps each other.
    Text(text = "My First Text in Compose",
        color = Color.White)

    Button(
        onClick = {}
    ) {
        Text("Button")
    }

    TextField(value = "TextField",
        onValueChange = {})    // EditText of xml.
}

//@Preview    // Preview always used when you want to see UI on preview panel.
//@Composable    // its a better choice to use preview function separately from composable bcz
//                           // in function, you have to pass parameters, which is becomes tough to view UI on panel.
//fun MyComposablePreview(){
//    MyFirstComposable()
//}

@Composable
fun ColumnExample(){   // Arrangement is always direction to parent layout like column and alignment direction is vice versa.
    Column(  modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.End
    )
    {    // to layout the items vertically, we use Column
    Text(text = "My First Text in Compose",
        color = Color.Red)

    Button(
        onClick = {}
    ) {
        Text(text = "Button")
    }

    TextField(value = "TextField",
        onValueChange = {})    // EditText of xml.
    }
}

//@Preview(showSystemUi = true, device = Devices.PIXEL_7)   // this show SystemUi is used to view UI on system mobile screen, also can change system device.
//@Composable
//fun ColumnExamplePreview(){
//    ColumnExample()
//}

@Composable
fun RowExample(){
    Row(  modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {   // to layout the items horizontally, we use Row.
        Text(text = "My First Text in Compose",
            color = Color.Green)

        Button(
            onClick = {}
        ) {
            Text( "Button")
        }
    }
}

//@Preview(showSystemUi = true, device = Devices.PIXEL_7)
//@Composable
//fun RowExamplePreview(){
//    RowExample()
//}


@Composable
fun BoxExample(modifier: Modifier = Modifier){
    Box(
        contentAlignment = Alignment.BottomEnd   // in box you only single alignment value which stores all 3X3 grid into in.
    ) {
     Image( // way to  use image.
         painter = painterResource(R.drawable.ic_launcher_background),
         contentDescription = null     // contentDescription is used when we want for talk-ability of a app for blind people in accessibility
        )
        Icon(imageVector = Icons.Default.LocationOn, contentDescription = null) // way to  use icons.
    }
}


//@Preview(showSystemUi = true, device = Devices.PIXEL_7)
//@Composable
//fun BoxExamplePreview(){
//    BoxExample()
//}


@Composable
fun ModifierExample(){
     // 1. Create a 50X50 box.
    //  2. Add a red background to the box.
   //   3. Add blue border around the box.

//    Box(
//        modifier = Modifier
//            // .size(100.dp)   //  size is used for height X Width for a item.
//            .height(150.dp)     // or you can use both separately.
//            .width(100.dp)
//            .background(Color.Red)
//            .border(4.dp, color = Color.Blue)
//    ){
//        val  context = LocalContext.current
//        Text(
//            modifier = Modifier.align(Alignment.TopCenter),
//            text = "Hello"
//        )
//        Text(
//            modifier = Modifier.align(Alignment.Center)
//                               .clickable {
//                                   Toast.makeText(context,"You've clicked on from ", Toast.LENGTH_SHORT).show()
//                               },
//            text = "From"
//        )
//        Text(
//            modifier = Modifier.align(Alignment.BottomCenter),
//            text = "Harry"
//        )
//
//    }


    // Box Circle in Modifier.

//    Box(
//        modifier = Modifier
//            .clip(CircleShape)
////            .clip(RoundedCornerShape(5.dp))
//            .size(100.dp)
//            .background(Color.Green, CircleShape)
//            .border(width = 5.dp, color = Color.Yellow, CircleShape),
//        contentAlignment = Alignment.Center
//    ){
//        Text(text = "Circle")
//    }


    // Box having concept of padding    // it shows that the order of writing code is differs from each others.

    Box(
        modifier = Modifier
            .size(100.dp)
            .background(Color.Blue)  // this how order matters of a code(Modifier) in compose.
            .border(width = 2.dp, Color.Black)  // this border will reflect on border of blue's box
            .padding(10.dp)
            .border(width = 2.dp, Color.Black),  // this border will reflect on border of Green's box.
        contentAlignment = Alignment.Center
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Green),
            contentAlignment = Alignment.Center
        ){
            Text(text = "Box")
        }

    }

}

@Preview
@Composable
fun ModifierExamplePreview(){
//    ModifierExample()
}