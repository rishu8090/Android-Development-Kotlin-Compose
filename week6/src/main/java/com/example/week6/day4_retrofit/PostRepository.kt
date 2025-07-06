package com.example.week6.day4_retrofit

import javax.inject.Inject

interface PostRepository {  // in viewModel we use this interface to get data from repository
    suspend fun getPosts(): List<Post>
}

class PostRepositoryImpl @Inject constructor(   // "PostRepositoryImpl is a class that takes postApi as a parameter and implements the PostRepository interface."
    private val postApi: PostApi
) : PostRepository {      // but in implementation we use this class to get data from api
    override suspend fun getPosts(): List<Post> {
        return  postApi.getPosts()
    }

//    override suspend fun getPosts(onSucces: (List<Post>) -> Unit): List<Post> {
//        val call = postApi.getPosts()
//        val response = call.execute()   // execute synchronously
//        return response.body()!!
//
//        call.enqueue(
//            object : Callback<List<Post>> {   // execute asynchronously
//                override fun onResponse(
//                    p0: Call<List<Post>?>,
//                    p1: Response<List<Post>?>
//                ) {
//                    onSucces(p1.body()!!)
//                    // paas these data to upper layers using callbacks.
//                }
//
//                override fun onFailure(
//                    p0: Call<List<Post>?>,
//                    p1: Throwable
//                ) {
//                    TODO("Not yet implemented")
//                }
//
//            }
//        )
//    }
}

/* in software development, it is pretty standard to use a interface class which uses another class as this interface implementation.
because lets assume we write a testing purpose code in here, and we doesn't want to make network call to test the code, in that case
 you can use PostRepositoryImpl class to test the code written without harming any part of the project,
 that's why it is pretty standard to use interface class to override the fun of the class in another Implementation class.
 this helps to make avoid concrete codebases.        // same for networkModule and RepositoryModule.
*
* */