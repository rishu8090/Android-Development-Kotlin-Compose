package com.example.week8.day1_workmanager

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class LogWorker(
    private val context: Context,
    private val workerParams: WorkerParameters
) : Worker(context, workerParams) {
    override fun doWork(): Result {
        val name = inputData.getString("Name")  // here we get the data from repository.

        Log.d(
            "TAG",
            "This is printed from ${this::class.simpleName}"
        )  // this will print class name.
        Log.d("TAG", "Input data name: $name")

        if (name == null) {
            val outputData = Data.Builder().putInt("age", 30).build()
            return Result.failure(outputData)
        }
        val outputData = Data.Builder().putInt("age", 28).build()
        return Result.success(outputData)  // if success we send outputData.
    }
}