package com.example.study

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.nio.charset.Charset

class ApplicationTests {

	@Test
	fun `eucKr으로 인코딩하고 UFT-8으로 디코딩하면 어떻게 될까`() {
		val originalString = "한글입니다.123456"

		val stringAsByteArray = originalString.toByteArray(charset = Charset.forName("euc-kr"))

		val utf8String = String(stringAsByteArray, Charsets.UTF_8)

		println(utf8String)

	}

}
