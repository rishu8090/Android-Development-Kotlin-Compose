package com.example.week8.day2_upload_image_workmanager

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker // A type annotation that identifies a androidx.work.ListenableWorker's constructor for injection.
class ImageUploadWorker @AssistedInject constructor(  // this worker extends workerFactory which can take only context and workParams, it will doesn't know how to create ImageUploadRepository.  // and how handle is defined in MyApplication
    @Assisted private val context: Context,  // to solve this problem we use AssistedInject, Assisted dependency means that we will help to assist, and rest of other dependency will take care by the hilt.
    @Assisted private val workerParams: WorkerParameters,
    private val respository: ImageUploadRepository
) : CoroutineWorker(
    context,
    workerParams
) {  // By using CoroutineWorker, we can use suspend function, it is a special type of Worker() method.
    override suspend fun doWork(): Result {

        val imageUriString = inputData.getString("uri") ?: return Result.failure()
        val response = respository.uploadImage(imageUriString) ?: return Result.failure()

        val outputData = Data.Builder()
            .putString("url", response.image.url)  // this will pass at many places.
            .build()
        return Result.success(outputData)
    }
}