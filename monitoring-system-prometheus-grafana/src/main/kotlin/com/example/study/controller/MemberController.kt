package com.example.study.controller

import com.example.study.domain.Member
import com.example.study.service.MemberService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/members")
class MemberController(
        private val memberService: MemberService,
) {

    @GetMapping("/{id}")
    fun getMemberById(@PathVariable id: Long): ResponseEntity<Member> {
        val member = memberService.findUserBy(id)
        return ResponseEntity.ok(member)
    }

    @PostMapping
    fun signUp(@RequestBody signUpRequest: SignUpRequest): ResponseEntity<Long> {
        val id = memberService.signUp(signUpRequest.histories)
        return ResponseEntity.ok(id)
    }

    @PutMapping("/{id}/history")
    fun addMemberHistories(
            @PathVariable id: Long,
            @RequestBody addHistoryRequest: AddHistoryRequest,
    ): ResponseEntity<Member> {
        val member = memberService.addMemberHistories(id = id, additionalHistories = addHistoryRequest.histories)
        return ResponseEntity.ok(member)
    }

    @DeleteMapping("/{id}")
    fun withdraw(@PathVariable id: Long): ResponseEntity<Unit> {
        memberService.withdraw(id = id)
        return ResponseEntity.noContent().build()
    }
}