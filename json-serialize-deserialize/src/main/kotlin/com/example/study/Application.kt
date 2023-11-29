package com.example.study

import com.fasterxml.jackson.annotation.*
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.kotlinModule
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.stereotype.Component
import java.util.*


@SpringBootApplication
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

@Configuration
class JacksonConfig {

//    @Bean
    fun objectMapperConfig(): ObjectMapper {
        return ObjectMapper()
            .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true)
//            .registerModule(kotlinModule())
            .registerModule(JavaTimeModule())
    }

    @Bean
    fun jsonCustomizer(): Jackson2ObjectMapperBuilderCustomizer {
        return Jackson2ObjectMapperBuilderCustomizer { builder: Jackson2ObjectMapperBuilder ->
            builder
                .visibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
//                .featuresToEnable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .modules(JavaTimeModule(), kotlinModule())
        }
    }


}


@Component
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
