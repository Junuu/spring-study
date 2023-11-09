package com.example.study

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableAsync
class ExecutorApplication

fun main(args: Array<String>) {
	runApplication<ExecutorApplication>(*args)
}
