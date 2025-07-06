package com.example.week6.day4_retrofit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NetworkingScreen(
    viewModel: PostViewModel = hiltViewModel()
) {
    val uiState by viewModel.posts.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getPosts()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "NetworkingScreen") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (uiState) {
                is NetworkingUiState.Loading -> {
                     LoadingIndicator(
                         modifier = Modifier.fillMaxSize()
                     )
                }

                is NetworkingUiState.Success -> {
                    val posts = (uiState as NetworkingUiState.Success).posts
                    PostList(
                        modifier = Modifier.fillMaxSize(),
                        posts = posts
                    )
                }

                is NetworkingUiState.Error -> {
                    val message = (uiState as NetworkingUiState.Error).message
                   ErrorView(
                       modifier = Modifier.fillMaxSize(),
                       message = message,
                       onRetryClick = { viewModel.getPosts() }
                   )
                }

                else -> {

                }

            }

        }
    }
}

@Composable
fun ErrorView(
    modifier: Modifier = Modifier,
    message: String,
    onRetryClick: () -> Unit
){
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Text(
            text = message,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center
        )
            Button(onClick = onRetryClick) {
                Text(text = "Retry")
            }
        }
    }
}

@Composable
fun LoadingIndicator(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator()
    }
}

@Composable
fun PostList(
    modifier: Modifier = Modifier,
    posts: List<Post>
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = posts,
            key = { post -> post.id }
        ) { post ->
            PostItem(
                modifier = Modifier,
                post = post
            )
        }
    }
}

@Composable
fun PostItem(
    modifier: Modifier = Modifier,
    post: Post
) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = post.id.toString(),
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.width(8.dp))

                Column {

                    Text(
                        text = post.title,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = post.body,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PostItemPreview() {
    PostItem(
        modifier = Modifier.fillMaxWidth(),
        post = Post(1, 123, "title", "body")
    )
}