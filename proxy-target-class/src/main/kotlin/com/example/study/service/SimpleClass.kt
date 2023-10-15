package com.example.study.service

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
 class SimpleClass: TestInterface {

    @Transactional
    override fun test(){

    }
}
