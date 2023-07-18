package com.example.study

import com.example.study.consumer.KafkaConsumer
import com.example.study.producer.KafkaProducer
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import java.util.concurrent.TimeUnit


@SpringBootTest
@ContextConfiguration(classes = [Application::class])
@ActiveProfiles("local")
class EmbeddedKafkaIntegrationTest(
	@Autowired private val producer: KafkaProducer,
	@Autowired private val consumer: KafkaConsumer,
) {

	@Test
	fun `임베디드 카프카 테스트`() {
		// given
		val payload = "embedded Kafka Test"

		// when
		producer.send("testTopic", payload)

		// then
		val result = consumer.latch.await(10L, TimeUnit.SECONDS)
		assertTrue(result)
		assertThat(consumer.saveLastPayload).isEqualTo(payload)
	}
}
