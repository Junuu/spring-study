package com.example.study.entity

import com.example.study.repository.TestEntityRepository
import jakarta.annotation.PostConstruct
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Slice
import org.springframework.data.domain.Window

@DataJpaTest
class TestEntityTest @Autowired constructor(
    private val testEntityRepository: TestEntityRepository,
){


    @PostConstruct
    fun `데이터 세팅`(){
        repeat(1000){
            testEntityRepository.save(TestEntity(name = it.toString()))
        }
    }

    @Test
    fun `Page 객체로 조회한다`(){
        val pageRequest = PageRequest.of(3,10)

        val actual: Page<TestEntity> = testEntityRepository.findPageBy(pageRequest)

        println(actual)
    }

    @Test
    fun `Slice 객체로 조회한다`(){
        val pageRequest = PageRequest.of(3,10)

        val actual: Slice<TestEntity> = testEntityRepository.findSliceBy(pageRequest)

        println(actual)
    }

    @Test
    fun `Window 객체로 조회한다`(){
        val pageRequest = PageRequest.of(3,10)

        val actual: Window<TestEntity> = testEntityRepository.findWindowBy(pageRequest)

        println(actual)
    }
}
