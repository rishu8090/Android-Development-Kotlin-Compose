package com.example.week8.day2_upload_image_workmanager

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImageUploadResponse(
    @Json(name = "status_code")
    val statusCode: Int,
    val image: Image
)

@JsonClass(generateAdapter = true)
data class Image(
    val name: String,
    val url: String,
)