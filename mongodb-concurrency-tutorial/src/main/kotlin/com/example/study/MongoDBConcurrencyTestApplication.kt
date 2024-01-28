package com.example.study

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class MongoDBConcurrencyTestApplication

fun main(args: Array<String>) {
	runApplication<MongoDBConcurrencyTestApplication>(*args)
}

