package com.example.week6.day1_day2_room

import android.util.Log
import kotlinx.coroutines.flow.Flow
import java.time.Instant
import javax.inject.Inject

class UserRepository
@Inject
constructor(
    private val userDatabase: UserDatabase, //   this repository is created to follow the mvvm architecture.
) {
    suspend fun insertUser(
        // in general, we can't access and network  the database from the main thread, to avoid this we use coroutines.
        username: String, // we make this fun as suspend, bcz we are using coroutines and using coroutines it helps us to run this fun in the background thread.
        email: String,
        fullName: String,
        address: Address,
        createdAt: Instant,
    ): Long {
        val user = User(
            username = username,
            email = email,
            fullName = fullName,
            address = address,
            createdAt = Instant.now()
        )
        val userId = userDatabase.userDao.insertUser(user)
        Log.d("TAG", "User inserted with ID: $userId")
        return userId
    }

    suspend fun updateUser(user: User) {
        val userId = userDatabase.userDao.insertUser(user)
        Log.d("TAG", "User inserted with ID: $userId")
    }

    suspend fun getAllUsers(): List<User> = userDatabase.userDao.getAllUsers()

    fun getAllUsersFlow(): Flow<List<User>> = userDatabase.userDao.getAllUsersFlow()

    suspend fun deleteUser(user: User): Int {
        return userDatabase.userDao.deleteUser(user)
    }

    suspend fun insertAadhaarCard(aadhaarCard: AadhaarCard){
        userDatabase.aadhaarCardDao.upsert(aadhaarCard)
    }

      fun getUserAndAadhaarCard(): Flow<List<UserAndAadhaarCard>> {
        return userDatabase.userDao.getUserAndAadhaarCard()
    }
}
