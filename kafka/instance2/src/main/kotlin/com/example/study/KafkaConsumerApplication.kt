package com.example.study

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync


@SpringBootApplication
@EnableAsync
class KafkaConsumerApplication

fun main(args: Array<String>) {
	runApplication<KafkaConsumerApplication>(*args)
}
