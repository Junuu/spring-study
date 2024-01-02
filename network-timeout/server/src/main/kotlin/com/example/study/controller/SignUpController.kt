package com.example.study.controller

import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.Thread.sleep
import java.util.UUID

@RestController
@RequestMapping("/members")
class SignUpController(
        private val memberRepository: MemberRepository,
) {

    @PostMapping
    fun signup(){
        memberRepository.save(Member())
        sleep(11* ONE_SECOND)
    }

    companion object{
        const val ONE_SECOND = 1000L
    }
}


@Entity
data class Member(
        @Id
        val id: String = UUID.randomUUID().toString(),
)

interface MemberRepository: JpaRepository<Member, String>