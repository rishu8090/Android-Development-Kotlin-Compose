package com.example.week4.day2

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.week4.R
import kotlinx.coroutines.launch

/*
* 1. Show a scroll to top component(Button) that takes user to top of the list/grid.
* 2. Only show it if the list is scrolled down.
* 3.
*
*
* */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  LazyGridExample(){
    val scope = rememberCoroutineScope()
    val gridState = rememberLazyGridState()
    val list = remember{ mutableStateListOf<Int>().apply{addAll(1..100)} }
   // val showScrollToTop by remember(gridState.firstVisibleItemIndex) { mutableStateOf(gridState.firstVisibleItemIndex > 0) }   // in that , we checks the condition all time when we scroll.
                                                                                                                                    // that why states changes rapidly and recomposition occurs many times , so we use below line to avoid such things.
    val showScrollToTop by remember{ derivedStateOf { gridState.firstVisibleItemIndex >0 } } // this will give us state only if when condition changes from true to false or false to true. which help to reduce recomposition
                                                                                             // derivedStateOf {  } is a special type of mutableStateOf which is here used.
//    LaunchedEffect(gridState.firstVisibleItemIndex) {
//        Log.d("TAG", "LazyGridExample : ${gridState.firstVisibleItemIndex}")
//    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "My TopAppBar") },
                navigationIcon = {
                    IconButton(onClick = {})
                    { Icon(Icons.Default.ArrowBack, null) }
                }
            )
        },

        floatingActionButton = {
            AnimatedVisibility(
                visible = showScrollToTop
            ) {
            FloatingActionButton(
                onClick = {
                    scope.launch {
                        gridState.animateScrollToItem(0)
                    }
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_arrow_upward_24),
                    contentDescription = null
                )
            }
          }
        }


    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ){
            LazyVerticalGrid(
                modifier = Modifier.weight(1f),    // after giving weight, firstly child of parent(Column) take all required space, after that all leftover space is given to this child( lazyVerticalGrid).
                state = gridState,
                columns = GridCells.Adaptive(minSize = 120.dp),  // this control horizontal length of card.
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                items(
                    items = list
                ){ item ->
                     GridItems(
                        modifier = Modifier
                            .size(120.dp),  // this control vertical length of card.
                        item = item.toString()
                    )
                }
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
fun GridItems(modifier: Modifier = Modifier, item: String){
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