package com.example.study

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


//@EnableBatchProcessing 넣으면 안됨
//@EnableBatchProcessing
@SpringBootApplication
class SpringBatchApplication

fun main(args: Array<String>) {
	runApplication<SpringBatchApplication>(*args)
}
