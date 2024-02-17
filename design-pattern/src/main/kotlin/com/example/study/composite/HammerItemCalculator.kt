package com.example.study.composite

import org.springframework.stereotype.Component

@Component
class HammerItemCalculator: ItemCalculator {
    override fun calculatePrice(): Long {
        return HAMMER_PRICE
    }
}

const val HAMMER_PRICE = 10_000L