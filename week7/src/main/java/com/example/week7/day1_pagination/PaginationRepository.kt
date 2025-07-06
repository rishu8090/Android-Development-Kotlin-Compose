package com.example.week7.day1_pagination

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//interface PaginationRepository{
//    suspend fun searchRepositories( page: Int, pageSize: Int): SearchRepositoryResponse
//}

//class PaginationRepositoryImpl @Inject constructor(
//    private val gitHubApi: GitHubApi
//): PaginationRepository {
//    override suspend fun searchRepositories(
//        page: Int,
//        pageSize: Int
//    ): SearchRepositoryResponse {
//       return gitHubApi.searchRepositories( page = page, pageSize = pageSize)
//    }
//}

class PaginationRepository @Inject constructor(
    private val api: GitHubApi
) {
    fun searchRepositories(
        pageSize: Int = 20
    ): Flow<PagingData<Repository>> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                initialLoadSize = 60,
                prefetchDistance = 20
            )
        ) {
            RepositoryPagingSource(api)
        }.flow
    }
}