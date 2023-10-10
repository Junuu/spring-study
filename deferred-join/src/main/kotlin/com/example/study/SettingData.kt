package com.example.study

import com.example.study.entity.MemberEntity
import com.example.study.log.logger
import com.example.study.repository.MemberJpaRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class SettingData(
    private val memberJpaRepository: MemberJpaRepository,
): ApplicationRunner{
    override fun run(args: ApplicationArguments) {
        repeat(500_000){
            logger.info { "saving count = $it" }
            memberJpaRepository.save(MemberEntity(name = "test$it"))
        }
    }
}
