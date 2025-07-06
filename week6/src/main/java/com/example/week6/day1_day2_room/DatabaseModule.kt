package com.example.week6.day1_day2_room

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // this module gives us the implementation of the UserDatabase class.
class DatabaseModule { // and if it gives implementation of the UserDatabase class, it means it also gives implementation of the UserDao class.
    @Provides
    @Singleton
    fun provideRoomDatabase(
        @ApplicationContext context: Context,
    ): UserDatabase = Room.databaseBuilder(context, UserDatabase::class.java, "user_database").build()

    @Provides
    @Singleton
    fun provideUserDao( // by the think of upper comment, we here provideRoomDatabase method as base and provideUserDao from it.
        userDatabase: UserDatabase
    ): UserDao = userDatabase.userDao

    @Provides
    @Singleton
    fun provideAadhaarCardDao(
        userDatabase: UserDatabase
    ): AadhaarCardDao = userDatabase.aadhaarCardDao

}

// DatabaseModule's Role: It's a configuration file for your dependency injection framework. It constructs and provides UserDatabase and UserDao instances so that UserRepository (and subsequently ViewModel) can receive them as dependencies.
