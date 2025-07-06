package com.example.week6.day4_retrofit

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)   // this is used to convert json to kotlin class and vice versa to counter problem of unable create converter.
data class Post(
        val id: Int,
        val userId: Int,
        val title: String,
        val body: String
)