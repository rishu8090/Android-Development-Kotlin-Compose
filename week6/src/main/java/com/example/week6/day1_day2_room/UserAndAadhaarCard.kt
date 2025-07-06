package com.example.week6.day1_day2_room

import androidx.room.Embedded
import androidx.room.Relation

data class UserAndAadhaarCard(
    @Embedded
    val user: User,  // Parent class marks as @Embedded
    @Relation(  // Child class marks as @Relation
        parentColumn = "id",   // parentColumn is the primary key of the parent class
        entityColumn = "userId"  // entityColumn is the foreign key of the child class
    )
    val aadhaarCard: AadhaarCard?
)
