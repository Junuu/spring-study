package com.example.study

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.exc.InvalidFormatException
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MSAEnumHandleApplicationTests {

	@Autowired
	lateinit var springBootDefaultObjectMapper: ObjectMapper

	@Test
	fun objectMapperTest0(){
		val result = springBootDefaultObjectMapper.readValue<Animal>("\"test\"")

		Assertions.assertEquals(result, Animal.정의되지않음)
	}

	@Test
	@DisplayName("objectMapper를 ")
	fun objectMapperTest1() {
		val customObjectMapper = jacksonObjectMapper()

		assertThrows<InvalidFormatException> {
			customObjectMapper.readValue<Animal>("\"test\"")
		}
	}

	@Test
	@DisplayName("")
	fun objectMapperTest2() {
		val customObjectMapper = jacksonObjectMapper()
			.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE)


		val result = customObjectMapper.readValue<Animal>("\"test\"")

		Assertions.assertEquals(result, Animal.정의되지않음)
	}

}
