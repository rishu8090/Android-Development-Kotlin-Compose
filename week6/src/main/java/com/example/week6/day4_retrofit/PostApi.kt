package com.example.week6.day4_retrofit

import retrofit2.http.GET

interface PostApi {  // retrofit is wrapper on the top of OkHttp
    @GET("posts")
    suspend fun getPosts(): List<Post>   /// suspend is used bcz it is unable to create network call adapter.

    //    fun getPosts() : Call<List<Post>>  // Call represents network call response and able to create network call adapter.
//    fun getPosts(): Response<List<Post>>

}

// @Get for retrieve data, @Post for send data. @Put for update data fully @Patch for update data partially, @Delete for delete data.
// https://developer.mozilla.org/en-US/docs/Web/HTTP/Reference/Methods   // documentation.