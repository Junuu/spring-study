package com.example.study.controller

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.atomic.AtomicBoolean


@RestController
class TestController {

    val threadPool: ThreadPoolExecutor = Executors.newFixedThreadPool(50) as ThreadPoolExecutor
    val spinLock = SpinLock()

    @PostMapping("/spin-lock-test")
    fun runSpinLock(){
        threadPool.submit {
            spinLock.doSomething()
        }
        println("current Active ThreadPool = ${threadPool.activeCount}")
    }

    @PostMapping("/spin-lock-end")
    fun shutdown(){
        threadPool.shutdown()
    }
}

@Component
class getProcceorCount(): ApplicationRunner {
    override fun run(args: ApplicationArguments) {
        println(Runtime.getRuntime().availableProcessors())
    }
}

class SpinLock {

    var sum = 0

    fun doSomething() {
        while(true){
            sum++
        }
    }
}
