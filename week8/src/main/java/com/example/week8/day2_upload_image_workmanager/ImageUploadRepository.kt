package com.example.week8.day2_upload_image_workmanager

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import androidx.core.net.toUri
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.MultipartBody
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class ImageUploadRepository @Inject constructor(
    private val imageApi: ImageApi,
    @ApplicationContext private val context: Context   /// Here, we use ApplicationContext, but you have to avoid there uses in Repository like module in real project.
) {
    suspend fun uploadImage(imageUriString: String): ImageUploadResponse? {

        try {
            val uri = imageUriString.toUri()  // Creates a Uri from the given encoded URI string.

            val imageString = context.getBase64StringFromUri(uri) ?: return null
            val requestBody =
                MultipartBody.Builder()   // we divide requestBody in multiple parts, by which it becomes easier to send and receive data. This also true in case of servers.
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("image", imageString)  //Add a form data part to the body.
                    .addFormDataPart("format", "json")
                    .build()

            return imageApi.uploadImage(body = requestBody)
        } catch (e: Exception) {
            return null
        }
    }

    fun Context.getBase64StringFromUri(uri: Uri): String? {   // it will change the image to base64 string by using ContentResolver.
        return this.contentResolver.openInputStream(uri)
            ?.use { inputStream ->  //  ContentResolver. -> // Enables logic that supports deprecation of _data columns, typically by replacing values with fake paths that the OS then offers to redirect to openFileDescriptor(Uri, String), which developers should be using directly.
                // Convert the image Uri to Bitmap.  // use that is the upper line works as let, but additional feature that is it automatically closes the inputStream.
                val imageBitmap: Bitmap? =
                    BitmapFactory.decodeStream(inputStream)  // returns null if uri is unable to convert to bitmap.
                imageBitmap?.let { bitmap ->
                    val byteArrayOutputStream = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
                    val byteArray = byteArrayOutputStream.toByteArray()
                    Base64.encodeToString(byteArray, Base64.DEFAULT)
                }
            }

    }

    // in above fun, we changes uri to inputStream and then to bitmap .After compress bitmap to ByteArrayOutputStream and byteArrayOutputStream to ByteArray, and lastly this byteArray to Base64 string..
}