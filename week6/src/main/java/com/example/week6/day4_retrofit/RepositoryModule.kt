package com.example.week6.day4_retrofit

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)   // this is used to provide implementation of the PostRepository interface
class RepositoryModule {
    @Provides
    fun providePostRepository(postApi: PostApi): PostRepository{
        return PostRepositoryImpl(postApi)
    }
}

//abstract class RepositoryModule{  //  you can also use this class in place of @Provides using binding
//    @Binds
//    abstract fun providePostRepository(Impl: PostRepositoryImpl): PostRepository
//}