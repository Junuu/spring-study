package com.example.study.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.kafka.test.EmbeddedKafkaBroker
import java.io.StringReader
import java.util.*


@Profile("beta")
@Configuration
class EmbeddedKafkaConfig{
    val topicName: String = "testTopic"

    @Bean
    fun broker(): EmbeddedKafkaBroker {
        val broker: EmbeddedKafkaBroker =
            EmbeddedKafkaBroker(count, controlledShutdown, partitions, topicName).zkPort(
                zookeeperPort
            ).kafkaPorts(9092).zkConnectionTimeout(EmbeddedKafkaBroker.DEFAULT_ZK_CONNECTION_TIMEOUT)
                .zkSessionTimeout(EmbeddedKafkaBroker.DEFAULT_ZK_SESSION_TIMEOUT)
        val properties = Properties()
        try {
            properties.load(StringReader(brokerProperties))
        } catch (ex: Exception) {
            throw IllegalStateException("Failed to load broker property from [$brokerProperties]", ex)
        }
        broker.brokerProperties(properties as Map<String, String>)

        return broker
    }

    private companion object {
        const val brokerProperties: String = "listeners=PLAINTEXT://localhost:9092"
        const val zookeeperPort: Int = 9093
        const val count: Int = 1
        const val partitions: Int = 3
        const val controlledShutdown = false
    }
}
