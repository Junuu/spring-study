package com.example.study

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")


//@Component
class CoroutineLogging: ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        runBlocking {
            val a = async {
                log("I'm computing a piece of the answer")
                6
            }
            val b = async {
                log("I'm computing another piece of the answer")
                7
            }
            log("The answer is ${a.await() * b.await()}")

        }
    }
}