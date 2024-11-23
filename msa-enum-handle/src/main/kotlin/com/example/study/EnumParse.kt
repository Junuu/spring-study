package com.example.study

import com.example.study.log.logger
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Component
class EnumParse(
    private val getNewAnimal: GetNewAnimal,
): ApplicationRunner{

    override fun run(args: ApplicationArguments?) {
        val correctInput = "CAT"
        val wrongInput = "HORSE"

        println(Animal.valueOf(correctInput))

        runCatching {
            println(Animal.valueOf(wrongInput))
        }.onFailure {
            logger.info("get error", it)
        }

        println(getNewAnimal.getNewAnimalWithEnum())
        println(Animal.safeValueOf(getNewAnimal.getNewAnimalWithString()))

    }
}

@FeignClient(
    name = "animal",
    url = "http://localhost:8080",
    dismiss404 = true,
)
interface GetNewAnimal {
    @GetMapping("/v1/new-animal")
    fun getNewAnimalWithEnum(): Animal

    @GetMapping("/v2/new-animal")
    fun getNewAnimalWithString(): String
}

enum class Animal {
    CAT,
    DOG,
    @JsonEnumDefaultValue 정의되지않음;

    companion object{

        @JsonCreator
        @JvmStatic
        fun safeValueOf(name: String): Animal{
            return values().find { it.name == name } ?: 정의되지않음
        }
    }

}

@RestController
class RestController{

    @GetMapping("/v1/new-animal")
    fun getAnimalNamesV1(): AnimalV2{
        return AnimalV2.HORSE
    }

    @GetMapping("/v2/new-animal")
    fun getAnimalNamesV2(): AnimalV2{
        return AnimalV2.HORSE
    }
}

enum class AnimalV2 {
    CAT,
    DOG,
    HORSE,
    @JsonEnumDefaultValue 정의되지않음
}