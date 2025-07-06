package com.example.week8.day1_workmanager

import android.util.Log
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.UUID
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class BackgroundWorkRepository @Inject constructor(  // this repository works as workRequest.
    private val  workManager: WorkManager   // this workManager requires @provides hilt annotation.
) {
    fun createOneTimeWorkRequest() {
        val oneTimeWorkRequest =
            OneTimeWorkRequest.Builder(LogWorker::class)  // here oneTimeWorkRequest is created.
                .setInitialDelay(5, TimeUnit.SECONDS)
                .build()
        workManager.enqueue(oneTimeWorkRequest)  // here, work is enqueue to workManager.
        val workId = oneTimeWorkRequest.id
        observerWorkStatus(workId)
    }

    fun createOneTimeWorkRequestWithDataAndConstraints() {   // work request with data and constraints,
        val inputData = Data.Builder() // it is internally a bundle.
            .putString("Name", "Sher")
            .build()
        val workConstraints = Constraints.Builder()
//             .setRequiresCharging(true)  // Sets whether device should be charging for the WorkRequest to run. The default value is false.
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val oneTimeWorkRequest =
            OneTimeWorkRequest.Builder(LogWorker::class)  // here oneTimeWorkRequest is created.
                .setInputData(inputData)
                .setConstraints(workConstraints)
                .setInitialDelay(5, TimeUnit.SECONDS)
                .build()

        workManager.enqueue(oneTimeWorkRequest)  // here, work is enqueue to workManager.

        val workId = oneTimeWorkRequest.id
        observerWorkStatus(workId)
    }

    fun createPeriodicWorkRequest(){
        val inputData = Data.Builder() // it is internally a bundle.
            .putString("Name", "Sher")
            .build()
                                                                                                       // Periodic work has a minimum interval of 15 minutes
        val periodicWorkRequest = PeriodicWorkRequest.Builder(LogWorker::class,15, TimeUnit.MINUTES)  // this will run by the workManager after each 30 min.
                .setInputData(inputData)
                .setInitialDelay(5, TimeUnit.SECONDS)
                .build()
        workManager.enqueue(periodicWorkRequest)  // here, work is enqueue to workManager.
        val workId = periodicWorkRequest.id
        observerWorkStatus(workId)
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun observerWorkStatus(workId: UUID){
        Log.d("TAG", "Observing the work status for work id: $workId")
        GlobalScope.launch {
            workManager.getWorkInfoByIdFlow(workId).collect { workInfo ->   // here collect flow.
                workInfo?.let { // to check condition ?

//                    when(workInfo.state){   // Remove the other when and use it. it is recommended to not use this type of commented things in your app.
//                        WorkInfo.State.ENQUEUED -> {  // it is used when you want to cancel the assigned work.
//                            workManager.cancelWorkById(workId)
//                        }
//                    }
                    when (workInfo.state) {

                        WorkInfo.State.SUCCEEDED -> {
                            val outputData = workInfo.outputData
                            val age = outputData.getInt("age", 0)
                            println("SUCCEEDED Age: $age")
                        }

                        WorkInfo.State.FAILED -> {
                            val outputData = workInfo.outputData
                            val age = outputData.getInt("age", 0)
                            println("SUCCEEDED Age: $age")
                        }
                        else -> {
                            println("State: ${workInfo.state}")
                        }
                    }
                }
            }
        }
    }
}


// in services, services are always alive, and monitor something, but in workManager it only lives when it use require and then stop,