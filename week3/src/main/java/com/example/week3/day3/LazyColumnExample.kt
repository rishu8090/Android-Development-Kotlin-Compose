    package com.example.week3.day3

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun ScrollableColumnExample(){
    val list = remember {  (1 ..100).map { "Item $it" } }
    val scrollState = rememberScrollState()

    Column(     // here, we don't recommend to use Column for large items of list, due to whn we use column in that case, all items of list is added,
        modifier = Modifier    // on the Column of scrollable screen, which is a case of wastage of resources. that why LazyLayout is used to tackle that problem in scrollable screen.
            .fillMaxSize()
            .verticalScroll(state = scrollState)
    ) {
        list.forEach{item ->
            ListItem(
                modifier = Modifier
                    .padding(vertical = 12.dp, horizontal = 8.dp)
                .fillMaxWidth(),
                item = item
            )
        }
    }
}

@Composable
fun PassComposableExample(
    modifier: Modifier = Modifier,
    counter : Int,
    onCounterChange: () -> Unit,
    textUi: @Composable () -> Unit    // you can also pass UI from other function by using this type of line.
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Text(text = "Counter: $counter")
        textUi()  // here, we will pass our UI which is send by other function.
        Button(onClick = {onCounterChange()}) {  // you can also use onCounterChange.invoke() instead of onCounterChange()
            Text("Increment Counter")
        }
    }
}

@Composable
fun ParentComposable(modifier: Modifier = Modifier){
    var  counter by remember { mutableIntStateOf(0) }
    PassComposableExample(
        modifier = Modifier.fillMaxSize(),
        counter = counter,
        onCounterChange = {
            counter++
        },
        textUi = {   // here, we passes our UI to child functions.   // and only that function can use this type of things , which have inbuilt content: @Composable parameter.
            Text(text =  "Counter: $counter")
        }
    )
}

@Preview
@Composable
fun ParentComposablePreview(){
    ParentComposable()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyColumnExample(modifier: Modifier = Modifier){
    val list = remember { (1..100).map{ "Item $it"} }

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),  // This padding will apply on the all four sides of the screen. (top, bottom, start, end)
        verticalArrangement = Arrangement.spacedBy(10.dp) // this padding will apply on the vertical axis of the listItems.
    ) {

//        item{
//            Text(text = "Header")  // in this way you can add a new item.
//        }

        stickyHeader {    // in this way, you added a new item in screen, which is stickyHeader, we can change its color
            ListItem(
                modifier = Modifier.fillMaxWidth(),
                item = "Header",
                backgroundColor = Color.Blue
            )
        }

         items(list){       // you can also this code instead of below code. Internally this code is simpler version of below code.
             item ->
             ListItem(
                 modifier = Modifier.fillMaxWidth(),
                 item = item
             )
         }

//        items(list.size){
//            index ->
//            ListItem(
//                modifier = Modifier.fillMaxWidth(),
//                item = list[index]
//            )
//        }
    }


}


@Preview
@Composable
fun  LazyColumnExamplePreview(){
    LazyColumnExample()
}

@Composable
fun ListItem(
    modifier: Modifier = Modifier,
    item: String,
    backgroundColor: Color = Color.Unspecified   //  for giving color to sticky header.
){
    Log.d("TAG", "ListItem: $item")
    Card (modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = backgroundColor)  // this is used to change the color of card for all cards but we use only for the sticky header here.
    ){
        Row(modifier = Modifier.padding(8.dp)) {   // here, we uses a row bcz on card we can't performs the padding operations,
            Text(text = item, style = MaterialTheme.typography.titleLarge)   // that why we uses Row by which we can use padding operations on Text
        }
    }
}

@Preview
@Composable
fun ScrollableColumnExamplePreview(){
    ScrollableColumnExample()
}