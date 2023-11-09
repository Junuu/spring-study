package com.example.study

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication



@SpringBootApplication
class ServerSentEventApplication

fun main(args: Array<String>) {
	runApplication<ServerSentEventApplication>(*args)
}
