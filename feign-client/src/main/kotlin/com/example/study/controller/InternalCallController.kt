package com.example.study.controller

import com.example.study.dto.ProfileResponse
import com.example.study.dto.ProfileDTO
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.IllegalArgumentException

@RestController
class InternalCallController {

    @GetMapping("/internal-call")
    fun internalCall(): ResponseEntity<ProfileResponse>{
        return ResponseEntity.ok(ProfileResponse(listOf(ProfileDTO("1","2"))))
    }

    @GetMapping("/internal-call-return-404")
    fun internalCallReturn404(): ResponseEntity<Unit>{
        return ResponseEntity.notFound().build()
    }

    @GetMapping("/internal-call-return-400")
    fun internalCallReturn400(): ResponseEntity<Unit>{
        return ResponseEntity.badRequest().build()
    }
}
