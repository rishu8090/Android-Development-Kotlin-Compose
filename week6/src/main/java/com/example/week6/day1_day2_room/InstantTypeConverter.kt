package com.example.week6.day1_day2_room

import androidx.room.TypeConverter
import java.time.Instant

class InstantTypeConverter {   // this class is used by the UserDatabase to convert Instant to String and vice versa.
    @TypeConverter
    fun fromInstant(data: Instant): String {   // A -> B
        return data.toString()
    }

    @TypeConverter
    fun toInstant(data: String): Instant{   // B -> A
        return Instant.parse(data)
    }

}