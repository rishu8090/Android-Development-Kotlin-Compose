package com.example.week8.day2_upload_image_workmanager

import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.ViewModel
import  android.net.Uri
import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ImageUploadViewModel @Inject constructor(
    private val repository: ImageUploadRepository,
    private val workManager: WorkManager
) : ViewModel() {
    private val _imageUrl = MutableStateFlow<String?>(null)
    val imageUrl: StateFlow<String?> get() = _imageUrl.asStateFlow()

//    fun uploadImage(imageUri: Uri) {
//        viewModelScope.launch(Dispatchers.IO) {
//            val response = repository.uploadImage(imageUri)
//            Log.d("TAG", "UploadImage Response -> $response")
//        }
//    }

    fun uploadImage(imageUri: Uri) {
        val inputData = Data.Builder()
            .putString(
                "uri",
                imageUri.toString()
            ) // this is passed to the ImageUploadWorker and retrieved by inputData.getString("uri")
            .build()

        val imageUploadWorkRequest =
            OneTimeWorkRequest.Builder(ImageUploadWorker::class)  // here we go flow of program.
                .setInputData(inputData)
                .build()

        workManager.enqueue(imageUploadWorkRequest)  // send request to workManager.

        val workId =
            imageUploadWorkRequest.id  // and by using id we can observe the status of work. reverseCall back to ImageUploadWorker.kt to viewModel.

        viewModelScope.launch {
            workManager.getWorkInfoByIdFlow(workId).collect {workInfo ->
                workInfo?.let {
                    when(workInfo.state){
                        WorkInfo.State.SUCCEEDED -> {
                            val imageUrl = workInfo.outputData.getString("url")
                            _imageUrl.value = imageUrl
                        }

                        WorkInfo.State.FAILED -> {

                        }

                         else -> {

                         }
                    }
                }

            }
        }
    }
}