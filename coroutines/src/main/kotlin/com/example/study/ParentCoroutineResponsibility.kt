package com.example.study

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class ParentCoroutineResponsibility: ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        runBlocking {
            // launch a coroutine to process some kind of incoming request
            val request = launch {
                repeat(3) { i -> // launch a few children jobs
                    launch  {
                        delay((i + 1) * 200L) // variable delay 200ms, 400ms, 600ms
                        println("Coroutine $i is done")
                    }
                }
                println("request: I'm done and I don't explicitly join my children that are still active")
            }
            request.join() // wait for completion of the request, including all its children
            println("Now processing of the request is complete")
        }
    }
}