package com.example.study

import org.junit.jupiter.api.Test

class AssociateAndGroupByTest {

    @Test
    fun `associateBy`(){
        val people = listOf(                                                     // 2
                Person("John", "Boston", "+1-888-123456"),
                Person("Sarah", "Munich", "+49-777-789123"),
                Person("Svyatoslav", "Saint-Petersburg", "+7-999-456789"),
                Person("Vasilisa", "Saint-Petersburg", "+7-999-123456"))


        val result1 = people.associateBy { it.city }
        println(result1)
        val result2 = people.associateBy (Person::city, Person::phone)
        println(result2)
        val result3 = people.associateBy ({it.city}, {it.phone})
        println(result3)

        val peopleCities = people.groupBy(Person::city, Person::name)            // 5
        println(peopleCities)
    }

    data class Person(val name: String, val city: String, val phone: String) // 1

}