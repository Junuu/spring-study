package com.example.study.composite

import org.springframework.stereotype.Component

@Component
class ItemCalculatorComposite(
        private val itemCalculator: List<ItemCalculator>
): ItemCalculator {
    override fun calculatePrice(): Long {
        return itemCalculator.sumOf { it.calculatePrice() }
    }
}