package com.example.week3.day3

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.intro_to_jetpack_compose.R

/*
* 1. A horizontally scrollable list of images.
* 2. Load image in a circular image view.
* 3. A circular, gradient based border around the image view.
* */


@Composable
fun InstagramStoryView(modifier: Modifier = Modifier){
    val stories = remember { (1..50).toList() }
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(stories){
            StoryItem(itemId = it)
        }
    }

}

// https://picsum.photos/id/237/200/300
@Composable
fun StoryItem(
    modifier: Modifier = Modifier,
    itemId: Int){
    val gradientColors =  listOf(Color.Red,Color.Yellow,Color.Magenta)  // gradient colors for border. this way you can create a gradient.

    Box(modifier = modifier
        .clip(CircleShape)
        .size(60.dp)
       // .border(2.dp, Color.Red, CircleShape)
        .border(width = 2.dp, brush = Brush.linearGradient(gradientColors), shape = CircleShape)
        .padding(5.dp)
        .background(Color.LightGray, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            modifier = Modifier
                .clip(CircleShape)
                .fillMaxSize(),
            model = " https://picsum.photos/id/${itemId}/200/300",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            placeholder =  painterResource(R.drawable.download),  // when images are loading, then this image will be used as temporary image.
            fallback = painterResource(R.drawable.download),
            onError = {
                Log.d("TAG","StoryItem: $itemId result: ${it.result.throwable.message}")
            }
        )
//        Text(text = itemId.toString(),
//            style = MaterialTheme.typography.titleMedium
//        )
    }
}

//@Preview
//@Composable
//fun StoryItemPreview( ){
//     StoryItem(
//         item = 1
//     )
//}

@Preview
@Composable
fun InstagramStoryViewPreview(){
    InstagramStoryView()
}