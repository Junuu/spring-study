package com.example.study.controller

import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class H2Connection(
    private val testRepository: TestRepository,
) {

    /*
        손쉽게 connection을 위해 get으로 정의, 원래는 post가 맞음
     */
    @GetMapping("/h2-connection")
    fun testConnection(){
        testRepository.save(Test("1"))
    }


}

@Repository
interface TestRepository: JpaRepository<Test, String>

@Entity
class Test(
    @Id
    val id: String
)
