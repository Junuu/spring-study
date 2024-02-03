package com.example.study

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component


@SpringBootApplication
class SpringCharsetReflectionAnnotation

fun main(args: Array<String>) {
	runApplication<SpringCharsetReflectionAnnotation>(*args)
}

//@Component
class CharsetTest: ApplicationRunner{
	override fun run(args: ApplicationArguments?) {
		val originalString = "That will cost €10.한글"
		val stringAsByteArray = originalString.toByteArray(charset = Charsets.UTF_8)

		val utf8String = String(stringAsByteArray, Charsets.UTF_16)
		val asciiString = String(stringAsByteArray, Charsets.US_ASCII)

		println(stringAsByteArray)
		println(utf8String)
		println(asciiString)
	}
}


object MyFunction{
	operator fun invoke(str: String): String{
		return str.lowercase() //소문자로 바꿔줌
	}

	operator fun plus(str: String): String{
		return "PREFIX$str"
	}
}

@Component
class InvokeTest: ApplicationRunner{
	override fun run(args: ApplicationArguments) {
		val upperCaseString = "HELLO WORLD"
		println(MyFunction.invoke(upperCaseString))
		println(MyFunction(upperCaseString))

		println(MyFunction.plus(upperCaseString))
		println(MyFunction + upperCaseString)
	}
}