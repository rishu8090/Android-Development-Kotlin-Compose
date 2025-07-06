package com.example.week8.day2_upload_image_workmanager

import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface ImageApi {
    @POST("upload") // @GET is used for fetch data to server, read only whereas @POST is used to send data to server to write
    suspend fun uploadImage(
        @Query("key") key: String = "6d207e02198a847aa98d0a2a901485a5",  // The key is the form field name used by the server to identify the uploaded file in a multipart/form-data request.
        @Body body: RequestBody  // source is the image path, it upload from a local path it requires RequestBody and if from a web it requires url.
    ): ImageUploadResponse
}


//  You don’t define the upload path on the Android (client) side.
//  The server defines where the uploaded file will be stored.
//📦  Android (Client) Side: ->
//  You only:
//  >Pick the image.
//  >Wrap it in a MultipartBody.Part.
//  >Send it to the API using Retrofit.