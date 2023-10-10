package com.example.study.controller

import com.example.study.service.DecreaseStockService
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DecreaseStockController(
    private val decreaseStockService: DecreaseStockService,
) {
    @PatchMapping("/stock")
    fun decreaseProductStock(): String{
        decreaseStockService.decrease()
        return "ok"
    }
}
