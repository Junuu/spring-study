package com.example.study

import com.example.study.log.logger
import kotlinx.coroutines.*
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity
import java.lang.Thread.sleep
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.TimeoutException

class CoroutineTimeoutTest {


    @Test
    fun `Future를 활용하여 비동기작업도 정리하기 - 예외 다루기`() {
        val startTime = System.currentTimeMillis()
        val result = executeWithTimeoutAndCancellationAsync(1500L) {
            throw NoSuchElementException("그냥 예외가 잘 처리되는지 확인")
            throw MyCustomException("예외가 발생한 이유는 먹힐까?")
        }
    }


    @Test
    fun `Future를 활용하여 비동기작업도 정리하기`() {
        val startTime = System.currentTimeMillis()
        try {
            val result = executeWithTimeoutAndCancellationAsync(1500L) {
                var i = 1L
                while (true) {
                    sleep(200L)
                    println("count : ${i++}")
                }
            }
            println(result)
        } catch (e: TimeoutException) {
            val endTime = System.currentTimeMillis()
            println("Timeout이 발생하는 시점은 시스템이 시작되고 ${endTime - startTime} 이후 이다.")
        }
        sleep(5000L)
    }

    @Test
    fun `Coroutine을 활용하여 비동기작업도 정리하기 - 동기적인 block이 취소에 협조적이지 않아서 안됨`() {
        val startTime = System.currentTimeMillis()
        try {
            val result = asyncAwaitWithTimeout(1500L) {
                var i = 1L
                while (true) {
                    delay(200L)
                    println("count : ${i++}")
                }
            }
            println(result)
        } catch (e: Exception) {
            val endTime = System.currentTimeMillis()
            println("Timeout이 발생하는 시점은 시스템이 시작되고 ${endTime - startTime} 이후 이다.")
        }
        sleep(5000L)
    }

    @Test
    fun `구조화된 동시성이 지켜지는 경우`() {
        val startTime = System.currentTimeMillis()
        try {
            runBlocking {
                val result = withTimeout(1000L) {
                    var i = 1L
                    while (i <= 5) {
                        SuspendMethodClass.printHelloWithSuspend(i)
                        i++
                    }
                }
                println(result)
            }
        } catch (e: Exception) {
            val endTime = System.currentTimeMillis()
            println("Timeout이 발생하는 시점은 시스템이 시작되고 ${endTime - startTime} 이후 이다.")
        }
        sleep(5000L)
    }

    @Test
    fun `asyncAwaitWithTimeout 메서드를 만들어서 템플릿화`() {
        val startTime = System.currentTimeMillis()
        try {
            val result = asyncAwaitWithTimeout(1000L) {
                var i = 1L
                while (i <= 5) {
                    SuspendMethodClass.printHelloWithSuspend(i++)
                }
            }
            println(result)
        } catch (e: Exception) {
            val endTime = System.currentTimeMillis()
            println("Timeout이 발생하는 시점은 시스템이 시작되고 ${endTime - startTime} 이후 이다.")
        }
        sleep(5000L)
    }


    @Test
    fun `Dispatchers IO는 어떻게 동작할까`() {
        val task = Callable {
            runBlocking {
                logger.info { " runBlocking Thread" }
                val jobs = List(100) {
                    CoroutineScope(Dispatchers.IO).async {
                        sleep(1000L)
                        logger.info { "job$it Thread" }
                    }
                }
                jobs.forEach { it.await() }
            }
        }

        runBlocking {
            val executorService = Executors.newFixedThreadPool(3)
            val result = executorService.submit(task)
            result.get()
        }
    }

    @Test
    fun `v4 - 또 다른 코루틴을 활용해야 한다`() {
        val startTime = System.currentTimeMillis()
        logger.info { "현재 쓰레드는 무엇일까 : ${Thread.currentThread().name}" }
        runBlocking {
            logger.info { "현재 쓰레드는 무엇일까 : ${Thread.currentThread().name}" }
            try {
                withTimeout(1000) {
                    logger.info { "현재 쓰레드는 무엇일까 : ${Thread.currentThread().name}" }
//                    CoroutineScope(Dispatchers.IO).async {
                    async {
                        logger.info { "현재 쓰레드는 무엇일까 : ${Thread.currentThread().name}" }
                        var i = 1L
                        while (i <= 5) {
                            runCatching {
                                NotSuspendMethodClass.httpRequest(i)
                            }
                            i++
                        }
                    }.await()
                }
            } catch (e: TimeoutCancellationException) {
                val endTime = System.currentTimeMillis()
                println("Timeout이 발생하는 시점은 시스템이 시작되고 ${endTime - startTime} 이후 이다.")
            }
        }

        Thread.sleep(5000L)
    }

    @Test
    fun `v3 - 코루틴 타임아웃에 http 메서드 호출해보기`() {
        runBlocking {
            withTimeout(500) {
                runInterruptible {
                    var i = 1L
                    while (i <= 5) {

                        NotSuspendMethodClass.httpRequest(i)

                        i++
                    }

                }
            }
        }
    }


    @Test
    fun `v2 - suspend 메서드가 아닌 코루틴 타임아웃 호출해보기`() {
        runBlocking {
            withTimeout(2000L) {
                runInterruptible {
                    var i = 1L
                    while (i <= 5) {
                        NotSuspendMethodClass.printHello(i++)
                    }
                }
            }
        }
    }

    @Test
    fun `suspend 메서드가 아닌 코루틴 타임아웃 호출해보기`() {
        runBlocking {
            withTimeout(2000L) {
                var i = 1L
                while (i <= 5) {
                    NotSuspendMethodClass.printHello(i++)
                }
            }
        }
    }

    @Test
    fun `suspend 메서드로 코루틴 타임아웃 호출해보기`() {
        runBlocking {
            withTimeout(2000L) {
                var i = 1L
                while (i <= 5) {
                    SuspendMethodClass.printHelloWithSuspend(i++)
                }
            }
        }
    }

    object NotSuspendMethodClass {

        fun printHello(i: Long): String {
            logger.info("method is called")
            Thread.sleep(1000)
            logger.info("$i 번째 hello")
            return "hello"
        }

        fun httpRequest(i: Long) {
            logger.info("method is called")
            Thread.sleep(1000L)
            var restTemplate = RestTemplate()
            restTemplate.getForEntity<String>("http://localhost:8080/test")
        }
    }

    object SuspendMethodClass {
        suspend fun printHelloWithSuspend(i: Long) {
            logger.info("method is called")
            delay(1000L)
            logger.info("$i 번째 hello")
        }
    }
}