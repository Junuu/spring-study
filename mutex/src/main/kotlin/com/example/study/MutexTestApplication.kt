package com.example.study

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling


@EnableScheduling
@SpringBootApplication
class MutexTestApplication

fun main(args: Array<String>) {
	runApplication<MutexTestApplication>(*args)
}
