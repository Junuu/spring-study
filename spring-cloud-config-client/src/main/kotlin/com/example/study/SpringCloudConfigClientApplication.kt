package com.example.study

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class SpringCloudConfigClientApplication

fun main(args: Array<String>) {
	runApplication<SpringCloudConfigClientApplication>(*args)
}
