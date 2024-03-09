package com.example.study

import org.junit.jupiter.api.Test

class FoldTutorialTest {

    @Test
    fun `reduce를 학습해보자`(){
        val numbers = (1..10).toList()

        val sumUsingReduce = numbers.reduce {total, num ->
            println("현재 총합 $total")
            println("총합에 더해진 값 ${total + num}")
            total + num
        }
        println(sumUsingReduce) // 55


        val emptyList = emptyList<Long>()

        val emptyResult = emptyList.reduceOrNull { total, num ->
            total + num
        }

        println(emptyResult) //null

        // java.lang.UnsupportedOperationException: Empty collection can't be reduced.
        emptyList.reduce{ total, num ->
            total + num
        }
    }

    @Test
    fun `fold를 학습해보자`(){
        val numbers = (1..10).toList()
        // total의 초기값은 100으로 시작한다.
        // fold는 초기값을 지정할 수 있다!!
        val sumUsingFold = numbers.fold(100) { total, num ->
            println("현재 총합 $total")
            println("총합에 더해진 값 ${total + num}")
            total + num
        }
        println(sumUsingFold) // 155

        val emptyList = emptyList<Int>()

        val emptyUsingFold = emptyList.fold(10){ total, num ->
            total + num
        }

        println(emptyUsingFold) //10

    }
}