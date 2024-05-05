package com.example.study

import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling


@SpringBootApplication
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor  = "PT30S")
class SpringSchedulerApplication

fun main(args: Array<String>) {
	runApplication<SpringSchedulerApplication>(*args)
}
