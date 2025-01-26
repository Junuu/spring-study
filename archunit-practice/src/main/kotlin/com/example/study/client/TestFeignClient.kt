package com.example.study.client

import com.example.study.annotation.AllowedClientUsingEnumReturnType
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(
    name = "localTestFeign",
    url = "http://localhost:8080",
    dismiss404 = true,
)
interface TestFeignClient {
    @GetMapping("/enum-response")
//    @AllowedClientUsingEnumReturnType
    fun getProfiles(): List<NestedClass>

    @GetMapping("/enum-response2")
    @AllowedClientUsingEnumReturnType
    fun getProduct(): NestedClassV2

    @GetMapping("/enum-safe")
    fun getProductSafe(): ProductStringResponse
}

data class NestedClass(
    val productResponse: ProductResponse,
    val someValue: String,
    val someInt: Int,
)

data class NestedClassV2(
    val productResponse: Map<String, Product>,
    val someValue: String,
    val someInt: Int,
)

data class ProductResponse(
    val product: Product,
)

enum class Product{
    PRODUCT_A,
    PRODUCT_B
}

data class ProductStringResponse(val product: String)

