package com.example.study

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.test.context.DynamicPropertyRegistry
import org.testcontainers.containers.KafkaContainer
import org.testcontainers.containers.wait.strategy.LogMessageWaitStrategy
import org.testcontainers.utility.DockerImageName
import java.time.Duration

@Import(Application::class)
@SpringBootApplication
@TestConfiguration(proxyBeanMethods = false)
class TestApiApplication {

    @Bean(destroyMethod = "stop")
    fun kafkaContainer(registry: DynamicPropertyRegistry): KafkaContainer {
        val kafkaContainer = KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.4.0"))
            .waitingFor(LogMessageWaitStrategy().withRegEx(".*\\[KafkaServer id=\\d+\\] started.*").withStartupTimeout(
                Duration.ofMinutes(2))); // Adjust the timeout as needed
        kafkaContainer.start()

        registry.add("spring.kafka.bootstrap-servers", kafkaContainer::getBootstrapServers)
        registry.add("spring.kafka.consumer.bootstrap-servers", kafkaContainer::getBootstrapServers)
        return kafkaContainer
    }
}

fun main(args: Array<String>) {
    runApplication<TestApiApplication>(*args)
}
