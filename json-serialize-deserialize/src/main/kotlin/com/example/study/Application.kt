package com.example.study

import com.fasterxml.jackson.annotation.*
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




//@Component
@Order(1)
class JsonParseApplicationRunner(
    private val objectMapper: ObjectMapper,
) : ApplicationRunner {
    override fun run(args: ApplicationArguments) {
        println("JsonParseApplicationRunner Start")

        val payload =
            objectMapper.writeValueAsString(MyInformation(name = "김준우", age = 27, email = "bababrll@naver.com"))

        println("payload = $payload")
        val parse = objectMapper.readValue(payload, MyInformation::class.java)
        println("parse = $parse")
    }
}

//@Component
//@Order(3)
class ByteParseApplicationRunner(
    private val objectMapper: ObjectMapper,
) : ApplicationRunner {
    override fun run(args: ApplicationArguments) {
        println("ByteParseApplicationRunner Start")
        val payload = objectMapper.writeValueAsBytes(AnotherClass())
        val byteArrayAsString = payload.joinToString(", ") { it.toString() }
        println("Byte Array as String: $byteArrayAsString")

        val parse = objectMapper.readValue(payload, MyIgnoreDTO::class.java)
        println(parse)
    }
}


data class MyIgnoreDTO(
    val col1: String,
    val col2: String,
    val col3: String,
)

data class AnotherClass(
    val col0: String = "col0",
    val col1: String = "col1",
    val col2: String = "col2",
    val col3: String = "col3",
    val col4: String = "col4",
)

data class MyInformation(
    val name: String,
    val age: Int,
    val email: String,
) {
}

data class InnerClass(
    val value: String,
)

data class MyInformationDeserialize(
    private val name: String = "김준우",
    val age: Int = 27,
)
