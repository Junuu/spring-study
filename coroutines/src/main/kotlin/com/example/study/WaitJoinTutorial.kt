package com.example.study

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

//@Component
class WaitJoinTutorial: ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        runBlocking {
            val job = launch { // launch a new coroutine and keep a reference to its Job
                delay(1000L)
                println("World!")
            }
            println("Hello")
            job.join() // wait until child coroutine completes
            println("Done")
        }
    }

}
