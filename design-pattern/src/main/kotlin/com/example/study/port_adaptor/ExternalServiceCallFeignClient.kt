package com.example.study.port_adaptor

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(
    name = "localTestFeign",
    url = "http://localhost:8081",
    dismiss404 = true,
)
interface ExternalServiceCallFeignClient {
    @GetMapping("/external-service-call")
    fun getExternalInformation(): String
}
