package com.example.study

import org.junit.jupiter.api.Test

class TakeIfTakeUnlessTest {

    @Test
    fun `takeIf, takeUnless 메서드 사용하기`(){
        val simpleNumber = 3
        println(simpleNumber.takeIf { true })
        println(simpleNumber.takeUnless { true })
    }

    @Test
    fun `takeIf, takeUnless 적합한 상황은 언제일까`(){
        val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val evenNumbers = numbers.filter { it % 2 == 0 }
        val oddNumbers = numbers.filterNot { it % 2 == 0 }

        val evenNumbers2 = numbers.mapNotNull { it.takeIf { number -> number % 2 == 0 } }
        val oddNumbers2 = numbers.mapNotNull { it.takeUnless { number -> number % 2 == 0 } }
    }
}