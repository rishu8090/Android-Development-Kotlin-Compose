package com.example.week7.day1_pagination

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkingModule {  // this is used to provide implementation of the PostApi interface.

    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {    // // an interceptor is a component that can observe, modify, or even short-circuit HTTP requests and responses.
        return OkHttpClient.Builder()   // An interceptor is a way to:  1 Add headers (e.g., Authorization)  2   Log request/response data  3  Retry requests 4   Modify the request or response before it's processed
            .addInterceptor(loggingInterceptor)  // there, work can be you see in the logcat.
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://api.github.com/")
            .addConverterFactory(MoshiConverterFactory.create())    // to counter problem of locate ResponseBody converter
            .build()
    }

    @Provides
    @Singleton
    fun provideGitHubApi(retrofit: Retrofit) = retrofit.create(GitHubApi::class.java)
}

