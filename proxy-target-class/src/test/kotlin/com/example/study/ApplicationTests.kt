package com.example.study

import com.example.study.service.MyTestService
import com.example.study.service.SimpleClass
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ApplicationTests {

	@Autowired
	lateinit var myTestService: MyTestService

	@Test
	fun contextLoads() {
		myTestService.test()
	}

}
