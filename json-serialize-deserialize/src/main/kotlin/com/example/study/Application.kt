package com.example.study

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import java.util.*


@SpringBootApplication
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}

@Component
@Order(1)
class FirstApplicationRunner(): ApplicationRunner{
	override fun run(args: ApplicationArguments?) {
		println("first")
	}
}

@Component
@Order(2)
class SecondApplicationRunner(): ApplicationRunner{
	override fun run(args: ApplicationArguments?) {
		println("second")
	}
}


@Component
@Order(1)
class JsonParseApplicationRunner(
	private val objectMapper: ObjectMapper,
) : ApplicationRunner {
	override fun run(args: ApplicationArguments) {
		println("ApplicationRunner Args: " + Arrays.toString(args.sourceArgs))

		val payload = objectMapper.writeValueAsString(AnotherClass())
		val parse = objectMapper.readValue(payload, MyIgnoreDTO::class.java)
		println(parse)
	}
}


@JsonIgnoreProperties(ignoreUnknown = true)
data class MyIgnoreDTO(
	@JsonProperty("col1")
	val col1: String,
	@JsonProperty("col2")
	val col2: String,
	@JsonProperty("col3")
	val col3: String,
)

data class AnotherClass(
	@JsonProperty("col0")
	val col0: String = "col0",
	@JsonProperty("col1")
	val col1: String = "col1",
	@JsonProperty("col2")
	val col2: String = "col2",
	@JsonProperty("col3")
	val col3: String = "col3",
	@JsonProperty("col4")
	val col4: String = "col4",
)
