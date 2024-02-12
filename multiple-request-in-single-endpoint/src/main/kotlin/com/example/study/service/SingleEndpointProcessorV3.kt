package com.example.study.service

import com.example.study.controller.request.SingleEndPointRequest
import org.springframework.stereotype.Component

interface SingleEndpointProcessor<out T: SingleEndPointRequest> {
    fun process(request: @UnsafeVariance T): String
}

@Component
class SingleEndpointDispatcherV3(
        private val singleEndpointProcessors: Map<String, SingleEndpointProcessor<SingleEndPointRequest>>,
): SingleEndpointProcessor<SingleEndPointRequest>{
    override fun process(request: SingleEndPointRequest): String {
        val processor = singleEndpointProcessors[request.type] ?: throw IllegalArgumentException("지원하지 않는 type 입니다")
        return processor.process(request)
    }
}




