package com.example.study

import org.junit.jupiter.api.Test

class DelegatePattern {

    @Test
    fun `delegate를 직접 구현해서 동물의 울음소리를 고양이 클래스에게 위임한다`(){
        println(Animal(Cat()).cry())
    }

        @Test
        fun `kotlin의 도움을 받은 delegate 구현`(){
            println(KotlinAnimalV1().cry())
            println(KotlinAnimalV2(KotlinCat()).cry())
        }

    class Animal(private val cat: Cat){
        fun cry() = cat.cry()
    }

    class Cat(){
        fun cry() ="야옹"
    }

    interface AnimalInterface{
        fun cry(): String
    }

    class KotlinCat(): AnimalInterface {
        override fun cry(): String {
            return "k야옹"
        }
    }

    class KotlinAnimalV1(): AnimalInterface by KotlinCat()
    class KotlinAnimalV2(private val kCat: KotlinCat): AnimalInterface by kCat

}