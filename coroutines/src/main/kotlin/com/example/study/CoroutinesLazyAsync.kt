package com.example.study

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import kotlin.system.measureTimeMillis

//@Component
class CoroutinesLazyAsync : ApplicationRunner {
    override fun run(args: ApplicationArguments) {
        runBlocking {
            val time = measureTimeMillis {
                val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
                val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() }
                one.start()
                delay(1000L)
                two.start()
                println("The answer is ${one.await() + two.await()}")
            }
            println("Completed in $time ms")
        }
    }

    suspend fun doSomethingUsefulOne(): Int {
        delay(1000L) // pretend we are doing something useful here
        return 13
    }

    suspend fun doSomethingUsefulTwo(): Int {
        delay(1000L) // pretend we are doing something useful here, too
        return 29
    }

}
