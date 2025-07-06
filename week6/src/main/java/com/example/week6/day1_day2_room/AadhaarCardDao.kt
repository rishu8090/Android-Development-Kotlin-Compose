package com.example.week6.day1_day2_room

import androidx.room.Dao
import androidx.room.Upsert

@Dao
interface AadhaarCardDao {
    @Upsert
    suspend fun upsert (aadhaarCard: AadhaarCard)
}