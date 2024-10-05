package com.example.study

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication



@SpringBootApplication
class SpinLockTestApplication

fun main(args: Array<String>) {
	runApplication<SpinLockTestApplication>(*args)
}
