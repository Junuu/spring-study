package com.example.study

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component


@SpringBootApplication
class SpringBeanNameRuleTestApplication

fun main(args: Array<String>) {
	runApplication<SpringBeanNameRuleTestApplication>(*args)
}

@Component
class BeanTest(
	private val applicationContext: ApplicationContext
): ApplicationRunner{
	override fun run(args: ApplicationArguments?) {
		applicationContext.getBean("z")
		applicationContext.getBean("fooBar")
		applicationContext.getBean("hTMLParser") // Java Beans Spec에 의해 빈 이름은 HTMLParser로 등록된다. 예외 발생
	}
}

@Component
class Z

@Component
class FooBar

@Component
class HTMLParser