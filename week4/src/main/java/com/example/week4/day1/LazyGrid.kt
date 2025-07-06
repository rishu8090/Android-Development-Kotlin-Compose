package com.example.week4.day1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun  LazyGridExample(){
//    val list = (1..100).toList()
//    val list = remember { mutableListOf<Int>().apply { addAll(1..50) } }
//      val list by remember{ mutableStateOf(mutableListOf<Int>().apply{addAll(1..60)} )}
//      var list by remember { mutableStateOf(mutableListOf<Int>().apply { addAll(1..60) }) }
    val list = remember{ mutableStateListOf<Int>().apply{addAll(1..100)} }
    /*  this mutableStateListOf creates a snapshot of list after each removal of items, this snapshot have different object from previous snapshot.
        by it creates a new object list at each time, state will change at each case , due to this state change recomposition will occur,
        and item will removed in both place in memory and UI by calling simple remove function.
     */



    Column(
        modifier = Modifier.fillMaxSize()
    ) {
    LazyVerticalGrid(
//        modifier = Modifier.fillMaxSize(),
//        columns = GridCells.Fixed(3),
        modifier = Modifier.weight(1f),    // after giving weight, firstly child of parent(Column) take all required space, after that all leftover space is given to this child( lazyVerticalGrid).
                columns = GridCells.Adaptive(minSize = 120.dp),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)

    ) {
        item(
            span = { GridItemSpan(maxLineSpan) }   // it will help to expand your item to no. of grids. // maxLineSpan helps you to span the item to whole available grids in a row.
        ){
            GridItem(
                modifier = Modifier.fillMaxWidth(),
                item = "Header"
            )
        }
        items(
            items = list
        ){ item ->
            GridItem(
                modifier = Modifier
                    .size(90.dp),
                item = item.toString()
                )
        }
     }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically){
            Button(onClick = {
                list.removeAt(10)
//                Log.d("TAG", "List = $list")

//                Log.d("TAG", "Before Deleting $list")
//                val obj1 = list
//                list.removeAt(10)
//                val tempList =  list.filterNot { it == 10 }
//                list = tempList.toMutableList()
//                val obj2 = list
//                Log.d("TAG", "After deleting $list")
//                Log.d("TAG", "equals ${obj1.equals(obj2)}?:")
            })
            {
                Text(text = "Delete Random Item")
            }
        }
    }
}

@Preview
@Composable
fun LazyGridExamplePreview(){
    LazyGridExample()
}

@Composable
fun GridItem(modifier: Modifier = Modifier, item: String){
    Card(modifier = modifier,
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(
            modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text(text = item,
                style = MaterialTheme.typography.titleLarge)
        }
    }
}

//@Preview
//@Composable
//fun GridItemPreview(){
//    GridItem(
//        modifier = Modifier.size(90.dp),
//        item = 1
//    )
//}