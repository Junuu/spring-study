package com.example.study

import kotlinx.coroutines.*
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

//@Component
class ExtractMethodTutorial: ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        runBlocking { // this: CoroutineScope
            launch { // launch a new coroutine and continue
                printWorldWithDelay()
            }
            println("Hello") // main coroutine continues while a previous one is delayed
        }
    }

    suspend fun printWorldWithDelay(){
        delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
        println("World!") // print after delay
    }
}
