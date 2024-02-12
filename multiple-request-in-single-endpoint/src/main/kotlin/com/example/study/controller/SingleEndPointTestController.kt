package com.example.study.controller

import com.example.study.controller.request.SingleEndPointRequest
import com.example.study.controller.request.SingleEndPointRequestWithCustomDeserializer
import com.example.study.service.SingleEndpointDispatcherV1
import com.example.study.service.SingleEndpointDispatcherV2
import com.example.study.service.SingleEndpointDispatcherV3
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SingleEndPointTestController(
        private val singleEndpointDispatcherV1: SingleEndpointDispatcherV1,
        private val singleEndpointDispatcherV2: SingleEndpointDispatcherV2,
        private val singleEndpointDispatcherV3: SingleEndpointDispatcherV3,
){

    @GetMapping("/v1/single-end-point")
    fun singleEndpointV1(@RequestBody singleEndPointRequest: Map<String, String>): String{
        singleEndpointDispatcherV1.process(singleEndPointRequest)
        return "hello"
    }

    @GetMapping("/v2/single-end-point")
    fun singleEndpointV2(@RequestBody singleEndPointRequestV2: SingleEndPointRequestWithCustomDeserializer): String{
        singleEndpointDispatcherV2.process(singleEndPointRequestV2)
        return "hello"
    }

    @GetMapping("/v3/single-end-point")
    fun singleEndpointV3(@RequestBody singleEndPointRequest: SingleEndPointRequest): String{
        singleEndpointDispatcherV3.process(singleEndPointRequest)
        return "hello"
    }
}
