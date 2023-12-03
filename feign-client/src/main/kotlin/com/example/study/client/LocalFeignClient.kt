package com.example.study.client

import com.example.study.dto.ClientProfileResponse
import com.example.study.dto.Profile
import feign.Response
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(
    name = "localTestFeign",
    url = "http://localhost:8080",
    dismiss404 = true,
)
interface LocalFeignClient {
    @GetMapping("/internal-call")
    fun getProfiles(): Response

    @GetMapping("/internal-call-return-404")
    fun get404()

    @GetMapping("/internal-call-return-400")
    fun get400(): Response
}
