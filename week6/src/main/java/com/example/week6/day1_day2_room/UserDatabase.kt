package com.example.week6.day1_day2_room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

// Base class for all Room databases. All classes that are annotated with Database must extend this class.
// Database class: It holds the database and serves as the main access point for the underlying connection to your app's persisted data.
@Database(
    entities = [User::class, AadhaarCard::class],
    version = 1,
    exportSchema = true,
)
//@AutoMigration(from = 1, to = 2)
@TypeConverters(  // The list of type converter classes. If converter methods are not static, Room will create an instance of these classes.
    value = [InstantTypeConverter::class]   // in that way, here we use InstantTypeConverter class.
)
abstract class UserDatabase : RoomDatabase() {
    abstract val userDao: UserDao // this is defined bcz in a program we can have multiple Dao's, and database must have to know all Dao's in the program.
    abstract val aadhaarCardDao: AadhaarCardDao
}
