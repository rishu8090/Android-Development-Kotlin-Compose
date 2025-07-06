package com.example.week6.day1_day2_room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao // Data Access Objects are the main classes where you define your database interactions
interface UserDao { // It provide methods that your app can use to query, update, insert, and delete data in the database.
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Upsert   // same meaning of the upper line // Insert + Update
    suspend fun insertUser(user: User): Long

    @Query("SELECT * FROM User") // this is one shot query and getAllUsersFlow is a reactive query.
    suspend fun getAllUsers(): List<User>

    @Query("SELECT * FROM User")
    fun getAllUsersFlow(): Flow<List<User>> // Suspend function should be called only from a coroutine or another suspend function.
    // but we want to use this fun as a variable that's why we doesn't declare it as suspend.

    @Delete
     suspend fun deleteUser(user: User): Int

    @Query("DELETE FROM User WHERE id = :userId")  // if you want to delete a user by id.
    suspend fun deleteUser(userId: Long): Int

    @Query("SELECT * FROM User")
//    suspend fun getUserAndAadhaarCard(): List<UserAndAadhaarCard>
     fun getUserAndAadhaarCard(): Flow<List<UserAndAadhaarCard>>
}
