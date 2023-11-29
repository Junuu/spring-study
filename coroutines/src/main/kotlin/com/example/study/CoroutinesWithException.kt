package com.example.study

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

//@Component
class CoroutinesWithException: ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        runBlocking {
            launch {
                println("start-coroutine")
                try{
                    throw IllegalArgumentException("코루틴 안에서 예외를 던지면 어떻게 될까")
                }catch (e : Exception){
                    println(e.localizedMessage)
                }
                println("end-corou" +
                        "tine")
            }
        }
    }
}
