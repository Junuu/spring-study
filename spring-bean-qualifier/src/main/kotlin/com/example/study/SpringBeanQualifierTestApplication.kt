package com.example.study

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component


@SpringBootApplication
class SpringBeanQualifierTestApplication

fun main(args: Array<String>) {
	runApplication<SpringBeanQualifierTestApplication>(*args)
}

@Component
class BeanTest(
	@Qualifier(BeanConfiguration.bean1_name)
	private val bean1: BeanInterface
): ApplicationRunner{
	override fun run(args: ApplicationArguments?) {
		println(bean1)
	}

}


@Configuration
class BeanConfiguration{

	@Bean(bean1_name)
	@ConditionalOnProperty(value = ["batch.enable"],  havingValue = "true")
	fun bean1(): BeanInterface{
		println("bean1 configuration")
		return Bean1()
	}

	@Bean(bean2_name)
	@ConditionalOnProperty(value = ["batch.enable"],  havingValue = "false")
	fun bean2(): BeanInterface{
		println("bean2 configuration")
		return Bean2()
	}

	@Bean(bean3_name)
	@ConditionalOnProperty(value = ["batch.enable"],  havingValue = "true")
	fun bean3(): BeanInterface{
		println("bean3 configuration")
		return Bean3()
	}

	companion object{
		const val bean1_name = "BEAN1"
		const val bean2_name = "BEAN2"
		const val bean3_name = "BEAN3"
	}
}

interface BeanInterface

class Bean1: BeanInterface

class Bean2: BeanInterface

class Bean3: BeanInterface