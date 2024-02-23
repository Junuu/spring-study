package com.example.study

import org.junit.jupiter.api.Test

class GenericTest {

    @Test
    fun `다양한 타입을 구현하기 위한 방법 - v1`(){
            val intValue: Int = IntBox(3).value
            intValue.plus(3)
            StringBox("Hello Generic")
    }

    @Test
    fun `다양한 타입을 구현하기 위한 방법 - v2`(){
        val anyValue: Any = SuperBox("예상하지 못한 타입").value
        if(anyValue is Int){
            println(anyValue.plus(3))
        }
        StringBox("Hello Generic")
    }

    @Test
    fun `다양한 타입을 구현하기 위한 방법 - v3`(){
        val stringValue: String = GenericBox("String").value
    }

    @Test
    fun `제네릭의 슈퍼타입`(){
        val `상위타입으로 캐스팅`: Animal = GenericBox(Cat()).value
//        val `하위타입으로 캐스팅`: KoreanCat = GenericBox(Cat()).value
    }

    @Test
    fun `Array - Animal은 인자로 받을 수 있지만 Dog는 받을 수 없다`(){
        val animalsWithDog: Array<Dog> = arrayOf(Dog())
        val animalsWithAnimal: Array<Animal> = arrayOf(Animal())
        receiveAnimal(animalsWithDog) //컴파일에러 발생
        receiveAnimal(animalsWithAnimal)
    }

    private fun receiveAnimal(animals: Array<out Animal>){
        println(animals.size)
//        animals[0] = Cat() //out을 붙이면 컴파일 에러가 발생한다!
    }

    @Test
    fun `List - Animal은 인자로 받을 수 있지만 Dog는 받을 수 없다`(){
        val animalsWithDog: List<Dog> = listOf(Dog())
        val animalsWithAnimal: List<Animal> = listOf(Animal())
        receiveAnimal(animalsWithDog)
        receiveAnimal(animalsWithAnimal)
    }


    private fun receiveAnimal(animals: List<Animal>){
        println(animals.size)
    }

    @Test
    fun `MutableList - Animal은 인자로 받을 수 있지만 Dog는 받을 수 없다`(){
        val animalsWithDog: MutableList<Dog> = mutableListOf(Dog())
        val animalsWithAnimal: MutableList<Animal> = mutableListOf(Animal())
        receiveAnimalWithMutableList(animalsWithDog)
        receiveAnimalWithMutableList(animalsWithAnimal)
    }


    private fun receiveAnimalWithMutableList(animals: MutableList<out Animal>){
        println(animals.size)
//        animals.add(Dog())
//        animals.add(Cat())
    }

    @Test
    fun `in 키워드 활용기`(){
        val cats = mutableListOf(Cat(),Cat(),Cat())
        val `최상위 부모로 변환하기` = mutableListOf(Any(), Any(), Any())
        copyFromTo(cats, `최상위 부모로 변환하기`) // Error! type mismatch - 컴파일 에러 발생
        println(`최상위 부모로 변환하기`)
    }

    private fun copyFromTo(from: MutableList<out Animal>, to: MutableList<in Animal>){
        for (i in from.indices) {
            to[i] = from[i]
        }
    }


    open class Animal()

    open class Cat: Animal(){
        fun cries(){
            println("야옹")
        }
    }

    open class Dog: Animal(){
        fun cries(){
            println("멍멍")
        }
    }

    class KoreanCat: Cat(){
        val nation = "한국"
    }

    data class GenericBox<T>(
            val value: T,
    )
    data class IntBox(
        val value: Int,
    )

    data class StringBox(
            val value: String,
    )

    data class SuperBox(
            var value: Any,
    )
}