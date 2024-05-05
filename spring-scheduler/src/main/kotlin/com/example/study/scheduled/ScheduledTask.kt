package com.example.study.scheduled

import com.example.study.log.logger
import net.javacrumbs.shedlock.core.LockAssert
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity
import java.lang.Thread.sleep

@Component
class ScheduledTask {
    @Scheduled(fixedDelay = 1000)
    fun run(){
        logger.info { "Hello ScheduledTask!" }
    }

    @Scheduled(fixedDelay = 1000)
    fun run2(){
        sleep(3000)
        logger.info { "Hello ScheduledTask2!" }
    }

    @Scheduled(fixedDelay = 1000 * 11)
    @SchedulerLock(name = "run3", lockAtLeastFor = "PT10S", lockAtMostFor = "PT10S")
    fun run3(){
        LockAssert.assertLocked();
        logger.info { "Hello ScheduledTask3 - processing!" }
        val result = RestTemplate().getForEntity<String>("http://httpbin.org/delay/10")
        logger.info { "Hello ScheduledTask3 - done!" }
    }

    @Scheduled(fixedDelay = 1000* 11)
    @SchedulerLock(name = "run3", lockAtLeastFor = "PT10S", lockAtMostFor = "PT10S")
    fun run4(){
        LockAssert.assertLocked();
        logger.info { "Hello ScheduledTask4 - processing!" }
        val result = RestTemplate().getForEntity<String>("http://httpbin.org/delay/10")
        logger.info { "Hello ScheduledTask4 - done!" }
    }
}

