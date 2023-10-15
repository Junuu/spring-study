package com.example.study.service

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class InterfaceImplementsClass: TestInterface {

    @Transactional
    override fun test() {
        println("hi")
    }
}
