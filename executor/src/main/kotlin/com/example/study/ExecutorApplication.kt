package com.example.study

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ExecutorApplication

fun main(args: Array<String>) {
	runApplication<ExecutorApplication>(*args)
}
