package com.example.study

import com.example.study.application.jpa.TestJpaEntity
import com.example.study.application.jpa.TestJpaEntityRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component


//@EnableBatchProcessing 넣으면 안됨
@SpringBootApplication
class SpringBatchApplication

fun main(args: Array<String>) {
	runApplication<SpringBatchApplication>(*args)
}

@Component
class InitDatabase(
	private val testJpaEntityRepository: TestJpaEntityRepository,
): ApplicationRunner {
	override fun run(args: ApplicationArguments) {
		repeat(10){
			testJpaEntityRepository.save(TestJpaEntity())
		}
		println()
	}
}
