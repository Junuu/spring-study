package com.example.study.service

import com.example.study.controller.request.SingleEndPointRequestWithCustomDeserializer
import org.springframework.stereotype.Component

interface SingleEndpointProcessorV2 {
    fun process(request: SingleEndPointRequestWithCustomDeserializer): String

    fun isSupport(type: String): Boolean
}


@Component
class SingleEndpointDispatcherV2(
        private val singleEndpointProcessors: List<SingleEndpointProcessorV2>,
): SingleEndpointProcessorV2 {
    override fun process(request: SingleEndPointRequestWithCustomDeserializer): String {
        val processor = singleEndpointProcessors.find { it.isSupport(request.type) } ?: throw IllegalArgumentException("지원되지 않는 타입")
        return processor.process(request)
    }

    override fun isSupport(type: String): Boolean {
        throw IllegalArgumentException("구현되지 않아야 함")
    }
}