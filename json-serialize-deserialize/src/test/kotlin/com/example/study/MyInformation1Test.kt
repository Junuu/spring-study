package com.example.study

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.kotlinModule
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime
//(classes = [JacksonConfig::class])
@SpringBootTest
class MyInformation1Test{

    @Autowired lateinit var objectMapper: ObjectMapper

    @Test
    fun test(){
        val given = MyInformation1(
            name = "김준우",
            age = 27,
            email = "bababrll@naver.com",
        )

        val result = objectMapper.writeValueAsString(given)

        println(result)
    }

    @Test
    fun test1(){
        val given = """
            {"name":"김준우","age":27,"email":"bababrll@naver.com"}
        """.trimIndent()

        val result = objectMapper.readValue(given,MyInformation1::class.java)

        println(result)
    }

    @Test
    fun `직렬화 역직렬화가 정상적으로 동작해야 한다`(){
        val objectMapper = ObjectMapper()
            .registerModule(JavaTimeModule())
            .registerModule(kotlinModule())
            .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        val serializedPerson = objectMapper.writeValueAsString(PersonFixture.get())
        Assertions.assertEquals(serializedPerson, """
            {"name":"김준우","age":27,"birthDay":[1997,12,18,0,0]}
        """.trimIndent())

        val person = objectMapper.readValue(serializedPerson, Person1::class.java)
        Assertions.assertEquals(person.age, 27)
        Assertions.assertEquals(person.name, "김준우")
    }
}
data class Person(
    private val name: String,
    val age: Int,
    val birthDay: LocalDateTime,
)

data class Person1(
    val name: String,
    val age: Int,
)

object PersonFixture{
    fun get(): Person{
        return Person(name = "김준우", age = 27, birthDay = LocalDateTime.of(1997,12,18,0,0,0))
    }
}
