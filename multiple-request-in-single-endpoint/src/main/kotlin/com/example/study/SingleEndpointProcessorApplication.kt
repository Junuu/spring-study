package com.example.study

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication



@SpringBootApplication
class SingleEndpointProcessorApplication

fun main(args: Array<String>) {
	runApplication<SingleEndpointProcessorApplication>(*args)
}
