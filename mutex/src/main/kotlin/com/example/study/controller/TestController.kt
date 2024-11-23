package com.example.study.controller

import com.example.study.log.logger
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import java.lang.Thread.sleep
import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.locks.LockSupport


@RestController
class TestController {

    val requestThread : ThreadPoolExecutor = Executors.newFixedThreadPool(REQUEST_HOLD_THREAD_COUNT) as ThreadPoolExecutor
    val threadPool: ThreadPoolExecutor = Executors.newFixedThreadPool(THREAD_COUNT) as ThreadPoolExecutor
    val worker = Worker()

    @PostMapping("/spin-lock-test")
    fun runSpinLock(){
        repeat(50){
            requestThread.submit {
                println("current Active requestThread = ${requestThread.activeCount}")
                val currentThread = Thread.currentThread()
                while(true){
                    if(threadPool.activeCount < THREAD_COUNT){
                        threadPool.submit {
                            worker.doSomething()
                            LockSupport.unpark(currentThread)
                        }
                        break
                    }
                    LockSupport.park()
                }
            }
        }
    }

    companion object{
        const val THREAD_COUNT = 1
        const val REQUEST_HOLD_THREAD_COUNT = 50
    }
}

class Worker {

    fun doSomething() {
        sleep(1000 * 10)
        println("job is finish")
    }
}

@Component
class CpuMonitoringScheduler {

    private val restTemplate = RestTemplate()
    private val cpuUsageThreshold = 0.001  // If CPU usage is below this threshold, display 0%

    @Scheduled(fixedRate = 5000)  // Every minute
    fun monitorCpuUsage() {
        val url = "http://localhost:8080/actuator/metrics/process.cpu.usage"
        // Get the response as CpuUsageResponse
        val response = restTemplate.getForObject(url, CpuUsageResponse::class.java)

        // Extract the CPU usage from the first measurement
        val cpuUsage = response?.measurements?.firstOrNull()?.value ?: 0.0

        // Check if the CPU usage is below the threshold and display 0% if so
        val displayCpuUsage = if (cpuUsage < cpuUsageThreshold) {
            0.0
        } else {
            cpuUsage
        }

        // Log the CPU usage (or 0% if below the threshold)
        logger.info("CPU Usage: ${"%.2f".format(displayCpuUsage * 100)}%")
    }
}

@Component
class ThreadDump: ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        sleep(1000 * 1000)
    }

}

data class CpuUsageResponse(
    val name: String,
    val measurements: List<Measurement>
)

data class Measurement(
    val statistic: String,
    val value: Double
)