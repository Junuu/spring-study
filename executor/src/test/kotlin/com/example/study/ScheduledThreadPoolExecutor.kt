package com.example.study

import org.junit.jupiter.api.Test
import java.time.LocalTime
import java.util.concurrent.*


class ScheduledThreadPoolExecutor {

    @Test
    fun `ExecutorService와 ScheduledExecutorService차이 이해하기`(){
        val fixedThreadPool: ExecutorService = Executors.newFixedThreadPool(2)
        val scheduledThreadPool: ScheduledExecutorService = Executors.newScheduledThreadPool(2)
    }

    @Test
    fun `schedule (Runnable) 튜토리얼`(){
        val scheduledThreadPool: ScheduledExecutorService = Executors.newScheduledThreadPool(2)

        // Job을 스케쥴링합니다.
        println("Scheduled task : " + LocalTime.now())
        val runnable = Runnable { println("Runnable task : " + LocalTime.now()) }
        val delay = 3L

        val result: ScheduledFuture<*> = scheduledThreadPool.schedule(runnable, delay, TimeUnit.SECONDS)
        println(result.isDone)
        println(result.getDelay(TimeUnit.SECONDS))
        println(result.get())
    }

    @Test
    fun `schedule (Callable) 튜토리얼`(){
        val scheduledThreadPool: ScheduledExecutorService = Executors.newScheduledThreadPool(2)

        // Job을 스케쥴링합니다.
        println("Scheduled task : " + LocalTime.now())
        val callable: Callable<String> = Callable { "Callable task : " + LocalTime.now()}
        val delay = 3L

        val result: ScheduledFuture<String> = scheduledThreadPool.schedule(callable, delay, TimeUnit.SECONDS)
        println(result.isDone)
        println(result.getDelay(TimeUnit.SECONDS))
        println(result.get())
    }

    @Test
    fun `scheduleAtFixedRate (Runnable) 튜토리얼`(){
        val scheduledThreadPool: ScheduledExecutorService = Executors.newScheduledThreadPool(2)

        // Job을 스케쥴링합니다.
        println("Scheduled task : " + LocalTime.now())
        val runnable = Runnable {
            println("++ Repeat task : " + LocalTime.now())
            sleepSec(4)
            println("-- Repeat task : " + LocalTime.now())
        }
        val delay = 3L



        val result: ScheduledFuture<*> = scheduledThreadPool.scheduleAtFixedRate(runnable, 2L, delay, TimeUnit.SECONDS)
        println(result.isDone)
        println(result.getDelay(TimeUnit.SECONDS))

        sleepSec(10)
        result.cancel(true)
    }

    private fun sleepSec(sec: Int) {
        try {
            TimeUnit.SECONDS.sleep(sec.toLong())
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }


    @Test
    fun `scheduleWithFixedDelay (Runnable) 튜토리얼`(){
        val scheduledThreadPool: ScheduledExecutorService = Executors.newScheduledThreadPool(2)
        scheduledThreadPool.awaitTermination(5, TimeUnit.SECONDS)

        // Job을 스케쥴링합니다.
        println("Scheduled task : " + LocalTime.now())
        val runnable = Runnable {
            var i  = 0
            println("++ Repeat task : " + LocalTime.now())
            sleepSec(4)
            println("-- Repeat task : " + LocalTime.now())
        }
        val delay = 3L

        val result: ScheduledFuture<*> = scheduledThreadPool.scheduleWithFixedDelay(runnable, 2L, delay, TimeUnit.SECONDS)
        println(result.isDone)
        println(result.getDelay(TimeUnit.SECONDS))

        sleepSec(18)
        result.cancel(false)
    }
}