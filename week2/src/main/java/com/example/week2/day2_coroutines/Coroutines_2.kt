package com.example.week2.day2_coroutines

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.time.Duration.Companion.seconds

suspend fun main(){
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Caught exception: ${throwable.localizedMessage}")
    }
//    val scope = CoroutineScope(EmptyCoroutineContext)
//    val scope = CoroutineScope(Dispatchers.IO + exceptionHandler)
    val scope = CoroutineScope(Dispatchers.IO + SupervisorJob() + exceptionHandler)  //A failure or cancellation of a child does not cause the supervisor job to fail and does not affect its other children

//    val parentJob = scope.launch(Dispatchers.Unconfined) {
//       val childJob = launch {
//            performAnotherWork(2)
//        }
//        performWork(1)
//    }

//    val job2 = scope.launch(Dispatchers.Unconfined) {
//        WaitAndPerformWork(1)
//    }

//    delay(5.seconds)
////    parentJob.cancel()  // if the parent job is cancelled, all its children are also cancelled..
//    scope.cancel() // similar if scope is cancelled, all its children are also cancelled.
//    parentJob.join()  // Note that the job becomes complete only when all its children are complete.


       val job1 =  scope.launch { performWork(3) }
       val job2 = scope.launch { performWorkAndThrowException(3) }

        job1.join()
        job2.join()

}

suspend fun performWork(count: Int){
    println("Started working from performWork")
    delay(5.seconds)
    println("This is printed from performWork $count: ${Thread.currentThread().name}")
}

suspend fun performWorkAndThrowException(count: Int){
    println("Started working from performWorkAndThrowException")
    delay(3.seconds)
    throw UnsupportedOperationException("By Intense Error")    // by this exception, we learn a thing that , if any of the child fail of the parent, the parent will also fail.
                                                                // in that case, performWork will be cancelled and after delay operation nothing will be printed.
}

suspend fun performAnotherWork(count: Int){
    println("Started working from performAnotherWork")
    delay(5.seconds)
    println("This is printed from performAnotherWork $count: ${Thread.currentThread().name}")
}

suspend fun WaitAndPerformWork(count: Int){
    println("Started working from WaitAndPerformWork")
    delay(10.seconds)
    println("This is printed from WaitAndPerformWork $count: ${Thread.currentThread().name}")

}