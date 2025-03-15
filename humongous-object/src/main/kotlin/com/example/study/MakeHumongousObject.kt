package com.example.study

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class MakeHumongousObject: ApplicationRunner {
    override fun run(args: ApplicationArguments) {
        val sizeInBytes = 8 * 1024 * 1024  // 3MB
        val largeArray = ByteArray(sizeInBytes) { it.toByte() }  // 3MB 크기의 배열 생성

        println("Large array of size ${largeArray.size} bytes created")
    }
}