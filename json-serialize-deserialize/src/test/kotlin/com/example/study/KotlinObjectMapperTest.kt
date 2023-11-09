package com.example.study

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class KotlinObjectMapperTest {

    data class Movie(
        val name: String,
        val studio: String,
        var rating: Float? = 1f,
    )

    @Test
    fun `kotlin에서 지원하는 jacksonObjectMapper를 사용해보기 직렬화`(){
        val objectMapper = jacksonObjectMapper()

        val movie = Movie("Endgame", "Marvel", 9.2f)
        val serialized = objectMapper.writeValueAsString(movie)

        val json = """
            {"name":"Endgame","studio":"Marvel","rating":9.2}
        """.trimIndent()

        assertEquals(serialized, json)
    }

    @Test
    fun `kotlin에서 지원하는 jacksonObjectMapper를 사용해보기 역직렬화`(){
        val objectMapper = jacksonObjectMapper()

        val serialized = """
            {"name":"Endgame","studio":"Marvel","rating":9.2}
        """.trimIndent()

        val actual = objectMapper.readValue<Movie>(serialized)

        assertEquals(9.2f, actual.rating)
    }

    @Test
    fun `ObjectMapper를 사용해보기`(){
        val objectMapper = ObjectMapper()
        objectMapper.registerModule(kotlinModule())

        val serialized = """
            {"name":"Endgame","studio":"Marvel","rating":9.2}
        """.trimIndent()

        val actual = objectMapper.readValue<Movie>(serialized)


        assertEquals(9.2f, actual.rating)
    }
}
