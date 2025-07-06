package com.example.week2.day2_coroutines

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {


     launch(Dispatchers.Unconfined) {
        doSomething(1)
    }

    launch {
        // fire-and-forget
        doSomething(2)
        doOtherThing(2)
    }



    val result: Deferred<String> = async { // an special form coroutine which used to return some value unlike launch{}.
        // return-value
        returnSomething()
    }

    println(result.await())  // Awaits for completion of this value without blocking a thread and resumes when deferred computation is complete, returning the resulting value or throwing the corresponding exception if the deferred was cancelled.

    println("This is printed from main:  ${Thread.currentThread().name}")
}

suspend fun doSomething(count: Int) {
    println("This is printed from doSomething $count: ${Thread.currentThread().name}")
    doOtherThing(count)
}

suspend fun doOtherThing(count: Int) {
    delay(5_000)
    println("This is printed from doOtherThing $count: ${Thread.currentThread().name}")
}

suspend fun returnSomething(): String {
    delay(5_000)
    return "This is printed from returnSomething: ${Thread.currentThread().name}"
}




/*
* by using suspend fun it will run in background thread and more fun is add in the stack for execution. once the execution of suspend fun completes
* it will again added to tech stack and execute it.
* suspend means you have a fun which is executing in process stack and you have to add more fun in the stack. then suspend fun go out of stack and run in background and other fun are added in stack.
* When you define a fun as suspend it gives guarantees that it will maintain the continuity of the fun.
*
*
* suspend fun can be only called from another suspend fun or from a coroutine.
*
* to avoid project going to coroutine hell(in which coroutines execution is in series and one after another, like we called any fun in a coroutine two times),
* To avoid this condition, we try to use coroutines in parallel which is done by calling this fun into a separate launch modes and this all launch mode is also called in a launch mode like,
* runBlocking{
* launch{doSomething()}
* launch{doSomething()}
* }
*
*
* coroutineScope  is defines how long the coroutine should run. and when we have to cancel it.
*
*
*
*
* */