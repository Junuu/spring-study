package com.example.study

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients


@SpringBootApplication
@EnableFeignClients
class DesignPatternApplication

fun main(args: Array<String>) {
	runApplication<DesignPatternApplication>(*args)
}
