package com.example.week6.day1_day2_room

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant

// Data entities: It represent tables in your app's database.
@Entity // you must have to annotate with @Entity for a type of data,  Marks a class as an entity
data class User(
    @PrimaryKey(autoGenerate = true) // you can view schema of this table in schemas folder by changing project view to Project.
    val id: Long = 0,
    val username: String,
    val email: String,
    @ColumnInfo(name = "Full Name") // it is used to change the name of the column in the database and it is optional.
    val fullName: String,
    @Embedded(prefix = "Address_")
    val address: Address,
    val createdAt: Instant  // Change A-> B   and then B-> A
)

data class Address(
    val city: String
)
