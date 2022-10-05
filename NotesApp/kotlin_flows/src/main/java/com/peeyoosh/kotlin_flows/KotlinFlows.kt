package com.peeyoosh.kotlin_flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking


// flow block builder : need to emit item explicitly
fun getNaturalNo() : Flow<Int> = flow {
    val list = listOf(0,1,2,3,4,5,6,7,8,9)
    list.forEach {
        delay(100)
        emit(it)
    }
}

// asFlow builder : no need to emit explicitly, it handles itself
fun getEvenNo() = listOf(2,4,6,8,10,12,14,16,18).asFlow()

// flowOf builder : no need to emit explicitly, it handles itself
fun getWeekDays() = flowOf("Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday")

fun main() {
    runBlocking {
        getNaturalNo().collect{
            println("Receiving No. : $it")
        }
        println("==================================")
        getEvenNo().collect{
            delay(200)
            println("Receiving Even No. : $it")
        }
        println("==================================")
        getWeekDays().collect{
            delay(500)
            println("Week day : $it")
        }

    }
}
class KotlinFlows {}