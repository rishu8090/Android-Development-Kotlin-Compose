package com.example.week6.day1_day2_room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AadhaarCard(
    @PrimaryKey
    val id: String,
    val userId: Long
)