package com.example.study.envers.repository

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class BoardJpaRepositoryTest{

    @Autowired lateinit var boardJpaRepository: BoardJpaRepository

    @Test
    fun `envers 첫 적용 테스트`(){
        boardJpaRepository.save(
            BoardJpaEntity(content = "본문1")
        )
    }
}
