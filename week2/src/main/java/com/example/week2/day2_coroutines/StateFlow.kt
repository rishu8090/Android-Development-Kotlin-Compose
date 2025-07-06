package com.example.week2.day2_coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class StateFlowExample{
    val scope = CoroutineScope(Dispatchers.IO)
    val stateFlow = MutableStateFlow(0)

    init{
        scope.launch {
            delay(5.seconds)
            repeat(5) { index ->
            //    println("Emitting $index")  // if you are not collecting the value.
                delay(1.seconds)
                stateFlow.value = (index + 1)
            }
        }
    }
}
suspend fun main(){
    val scope = CoroutineScope(Dispatchers.IO)
    val stateFlowExample = StateFlowExample()

    println("Starting the collection of stateFlow")
    scope.launch{

        stateFlowExample.stateFlow.collect{
            println("Received $it")
        }

    }.join()
}

/*
*       Flow
*       1. cold flow
*       2. can contains n numbers of items.
*       3. instances dies after flow completion.
*
*       SharedFlow
*       1. hot flow
*       2. can contains n numbers of items.
*       3. instances should live after flow completion.
*
*       StateFlow
*       1. hot flow
*       2. can contains 1 number of items.
*       3.instances should live after flow completion.
* */