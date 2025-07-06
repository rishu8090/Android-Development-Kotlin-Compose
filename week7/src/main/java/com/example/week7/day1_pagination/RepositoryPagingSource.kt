package com.example.week7.day1_pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import javax.inject.Inject


const val First_Page = 1

class RepositoryPagingSource @Inject constructor(
    private val api: GitHubApi
) : PagingSource<Int, Repository>() {
    override fun getRefreshKey(state: PagingState<Int, Repository>): Int? {     // here Key is the page number
        return null  //  it gives key to the load fun only for the first time.
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repository> {
        try {
            val page = params.key
                ?: 1 // first time it is null which is given by the getRefreshKey fun, if it is null then it request for the 1st page.
            val pageSize = params.loadSize
            val response = api.searchRepositories(page = page, pageSize = pageSize)

            return LoadResult.Page(  //Result of a load request from PagingSource.load.
                data = response.items,
                prevKey = if (page == First_Page) null else page.minus(1),  // if it is the first page then prevKey is null.
                nextKey = if (response.items.isNotEmpty()) page.plus(1) else null // if it is the last page then nextKey is null.
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}