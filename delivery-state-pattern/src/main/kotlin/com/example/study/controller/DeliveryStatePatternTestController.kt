package com.example.study.controller

import com.example.study.service.DeliveryStatePatternTestService
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

@RestController
class DeliveryStatePatternTestController(
    private val deliveryStatePatternTestService: DeliveryStatePatternTestService,
) {

    @PutMapping("/change-state")
    fun changeState(@RequestBody changeStateRequest: ChangeStateRequest): String{
        return deliveryStatePatternTestService.changeStatus(
            deliveryId = "1",
            invoiceCode = changeStateRequest.invoiceCode,
            behavior = changeStateRequest.behavior,
        )
    }
}

data class ChangeStateRequest(
    val behavior: String,
    val invoiceCode: String? = null,
)
