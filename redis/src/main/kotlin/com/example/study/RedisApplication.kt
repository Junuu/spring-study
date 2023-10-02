package com.example.study

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy


@SpringBootApplication
@EnableAspectJAutoProxy
class RedisApplication

fun main(args: Array<String>) {
	runApplication<RedisApplication>(*args)
}
