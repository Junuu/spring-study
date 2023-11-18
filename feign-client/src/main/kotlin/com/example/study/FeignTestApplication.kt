package com.example.study

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients


@SpringBootApplication
@EnableFeignClients
class FeignTestApplication

fun main(args: Array<String>) {
	runApplication<FeignTestApplication>(*args)
}
