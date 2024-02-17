package com.example.study.composite

import org.springframework.stereotype.Component

@Component
class PhoneItemCalculator: ItemCalculator{
    override fun calculatePrice(): Long {
        return PHONE_PRICE
    }
}

const val PHONE_PRICE = 100_000L