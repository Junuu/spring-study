package com.example.study

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients


@EnableFeignClients
@SpringBootApplication
class MSAEnumHandleApplication

fun main(args: Array<String>) {
	runApplication<MSAEnumHandleApplication>(*args)
}
