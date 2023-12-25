package com.example.study.service

import com.example.study.domain.Member
import com.example.study.repository.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberService(
        private val memberRepository: MemberRepository,
){
    fun findUserBy(id: String): Member {
        return memberRepository.findByIdOrNull(id) ?: throw IllegalArgumentException("${id}로 회원을 조회할 수 없습니다")
    }

    @Transactional
    fun signUp(memberHistories: List<String>): String{
        val histories = memberHistories.map { it to it }.toMap().toMutableMap()
        val member = Member(histories = histories)
        return memberRepository.save(member).id!!
    }

    @Transactional
    fun addMemberHistories(id: String, additionalHistories: List<String>): Member{
        val member = memberRepository.findByIdOrNull(id) ?: throw IllegalArgumentException("${id}로 회원을 조회할 수 없습니다")
        member.addHistories(additionalHistories)
        return memberRepository.save(member)
    }

    @Transactional
    fun withdraw(id: String){
        memberRepository.deleteById(id)
    }

}