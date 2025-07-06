package com.example.week7.day1_pagination

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaginationScreen(
    viewModel: PaginationViewModel = hiltViewModel()
) {
    val repositories: LazyPagingItems<Repository> =
        viewModel.repositories.collectAsLazyPagingItems()
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "PaginationScreen") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RepositoryList(
                modifier = Modifier.fillMaxSize(),
                repositories = repositories
            )
        }
    }
}


@Composable
fun RepositoryList(
    modifier: Modifier = Modifier,
    repositories: LazyPagingItems<Repository>
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(repositories) { repository ->
            RepositoryItem(
                modifier.fillMaxWidth(),
                repository = repository
            )
        }
        repositories.apply {
            if (loadState.refresh is LoadState.Loading) {  // when you request for the first time or First page.
                item {
                    LoadingIndicator(modifier = Modifier.fillParentMaxSize())   // if we use fillMaxSize() it only uses the space which is used by the single item, but if we use this parent it will used space used by the entire column.
                }
            }

            if (loadState.append is LoadState.Loading) {
                item {
                    LoadingIndicator(modifier = Modifier.fillMaxWidth())
                }
            }

            if (loadState.refresh is LoadState.Error) {
                item {
                    ErrorItemWithRetry(
                        text = "Something went wrong!",
                        modifier = Modifier.fillParentMaxSize(),
                        onRetryClick = {
                            retry()
                        }
                    )
                }
            }

            if(loadState.append is LoadState.Error){
                item {
                    ErrorItemWithRetry(
                        text = "Something went wrong!",
                        modifier = Modifier.fillMaxWidth(),
                        onRetryClick = {
                            retry()
                        }
                    )
                }
            }

        }
    }
}


@Composable
fun RepositoryItem(
    modifier: Modifier = Modifier,
    repository: Repository
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
                    text = repository.id.toString(),
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.width(8.dp))

                Column {

                    Text(
                        text = repository.name,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.width(4.dp))

                    repository.description?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}

/*
* Screen     viewModel                            PagingRepository            RepositoryPagingSource              GitHubApi(github link(Server))  which uses NetworkModule to provide the api
* app   <-  ViewModel    <-     PagingData  <- Pager(PagingConfig.)   <=>       PagingSource            <=>          api
*
*
* paging config. contains all the info about the paging like page and page Number in one frame and how much pages downloaded in backup.
* */