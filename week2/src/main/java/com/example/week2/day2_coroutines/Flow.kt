package com.example.week2.day2_coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlin.time.Duration.Companion.seconds

suspend fun main() {

  //  val numbers = getNumbers()   // if you only calls this fun as var and not use var anywhere, then in that case, fun will work similar for sending.
//    println(numbers)
//         numbers
//        .forEach {
//            delay(0.3.seconds)
//            println("Receiving $it")
//        }



    val numbers = getNumbersFlow()    // only calling like upper fun will not work in case of flow.
            numbers                  // but it you emit the number and doesn't collect it, then in that case nor emit nor collect will work, they only work when collect is called.
            .collect {        //  that's why it is known as cold flow.
            delay(0.3.seconds)
            println("Receiving $it")
        }

     println("By second receiver")

    numbers.collect {  // here, we use two collect with a single emit function. if more than two collect are used in that case firstly emit function works,
        delay(0.5.seconds)  // simultaneously with first collect fun and after completing first collect fun, second collect fun works.
        println("Receiving (2) $it")
    }

    numbers
        .onEach {// by using this way, you can emit the value by a single emit function, and can create collect this emit by two times at same time.
            delay(1.seconds)  // means here, a single emit fun and having two collect fun, which works at same time.
            println("Receiving (3) $it")
        }.collect {
            delay(1.seconds)
            println("Receiving (4) $it")
        }

}

suspend fun getNumbers(): List<Int> {
//    return (1..50).map{"Item ${it}"}.toList() // this way you can get Item 1 to Item 50
    return (1..50)
        .onEach {
            delay(0.3.seconds)
            println("Sending $it")
        }
        .toList()
}


        // flow can emit, integer, string, list or anything not only a single value.
suspend fun getNumbersFlow(): Flow<Int> = flow {// Kotlin Flow is a library for asynchronous stream processing in Kotlin.
                                                                    // It allows you to emit a stream of values asynchronously and handle them in a sequential manner.
    (1..5)
        .onEach {
            delay(0.3.seconds)   ///  biggest advantage of using flow is it, the value is emitted will be collected by the collect function at same time when it is emitted. q q
            println("Sending $it")
            emit(it)
        }
}