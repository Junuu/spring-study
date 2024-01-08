package com.example.study

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

//@Component
class CoroutinesLecture1: ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        runBlocking {
            println("START")
            launch {
                newRoutine()
            }
            yield()
            println("END")
        }
    }

    suspend fun newRoutine(){
        val num1 = 1
        val num2 = 2
        delay(50)
        println("${num1 + num2}")
    }
}