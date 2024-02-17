package com.example.study.composite

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class CompositeClient(
    private val itemCalculatorComposite: ItemCalculatorComposite,
): ApplicationRunner {

    override fun run(args: ApplicationArguments) {
        val price = itemCalculatorComposite.calculatePrice()
        println("총 가격은 : $price 입니다")
    }
}