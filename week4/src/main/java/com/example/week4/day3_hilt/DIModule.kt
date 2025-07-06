package com.example.week4.day3_hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module  // after providing @Module hilt will search all Provide annotations in this class or object.
@InstallIn(SingletonComponent::class)   // it decides, how long this class lives  // singleton decides how much instances are created.
class DIModule {
    @Provides   // Provide is used bcz, her this fun provides a instance of AnotherDIRepository.
     fun provideAnotherDIRepository(): AnotherDIRepository{
         return AnotherDIRepository.getClassInstance()  // creating instance of AnotherDIRepository by help of static objects
     }
}