package com.example.week4.day1

import android.util.Log
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
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun  KeyLazyGridExample(){
    val list = remember{ mutableStateListOf<Int>()
        .apply{
            addAll(1..100)
        }
    }
    /*  this mutableStateListOf creates a snapshot of list after each removal of items, this snapshot have different object from previous snapshot.
        by it creates a new object list at each time, state will change at each case , due to this state change recomposition will occur,
        and item will removed in both place in memory and UI by calling simple remove function.
     */

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyVerticalGrid(
            modifier = Modifier.weight(1f),    // after giving weight, firstly child of parent(Column) take all required space, after that all leftover space is given to this child( lazyVerticalGrid).
            columns = GridCells.Adaptive(minSize = 120.dp),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)

        ) {
            item(
                span = { GridItemSpan(maxLineSpan) }   // it will help to expand your item to no. of grids. // maxLineSpan helps you to span the item to whole available grids in a row.
            ){
                KeyGridItem(
                    modifier = Modifier.fillMaxWidth(),
                    item = "Header",
                    backGroundColor = Color.Magenta
                )
            }
//            items(
//               items = list,
//                key = {itemValue -> itemValue}  // this will track item by its value.
//                ) { item ->
//                KeyGridItem(
//                    modifier = Modifier.size(100.dp),
//                    item = item.toString(),
//                    backGroundColor = if(item % 2 == 0) Color.Green else Color.Blue  // Same thing you also can done using the indexes of the item just like we did in below code.
//                )
//            }

            itemsIndexed(
                items = list,
                key = { index, item -> item }  // key and value pair.
//                key = { item -> item }
            ) { index, item ->
                KeyGridItem(
                    modifier = Modifier.size(100.dp),
                    item = item.toString(),
                    backGroundColor = if (index % 2 == 0) Color.Green else Color.Blue
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
            })
            {
                Text(text = "Delete Random Item")
            }
        }
    }
}

@Composable
fun KeyGridItem(modifier: Modifier = Modifier,
                item: String,
                backGroundColor: Color = Color.Unspecified ){
    Log.d("TAG", "Recomposing Item: $item")
    /*
    Before, for upper log call, we doesn't use key mechanism, In that, item is managed by the index position of the item, that why when we
    delete something for all item shifts from one index, that why they treated as new item due to this recomposition will occur from deleted item to new
    incoming item, which is not good, that's why we use key in which we track item by its value, that when a item deleted only new incoming item will recompose.
    * */
    Card(modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = backGroundColor)
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

@Preview
@Composable
fun KeyLazyGridExamplePreview(){
    KeyLazyGridExample()
}