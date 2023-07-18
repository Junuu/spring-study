package com.example.study.service

import org.springframework.stereotype.Component
import java.lang.IllegalStateException

@Component
class SignUpService {
    fun throwException(){
        throw IllegalStateException()
    }
}
