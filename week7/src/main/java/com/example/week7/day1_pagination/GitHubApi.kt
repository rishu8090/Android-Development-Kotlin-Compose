package com.example.week7.day1_pagination

import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApi {
//    @GET("search/repositories?q=language:kotlin&sort=stars&order=desc&per_page=50")
//    @GET("search/repositories?q=language:kotlin&sort=stars&order=desc&per_page=50")
//    suspend fun searchRepository(
//        query: String,
//        page: Int,
//        pageSize: Int,
//    ): SearchRepositoryResponse

    @GET("search/repositories")
    suspend fun searchRepositories(
        @Query("q") query: String = "language:kotlin",
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int
    ): SearchRepositoryResponse
}