package com.example.study.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
final class MyTestService(
    private val interfaceImplementsClass: TestInterface,
) {

//    @Autowired
//    lateinit var simpleClass: SimpleClass



    fun test(){
//        simpleClass.test()
        interfaceImplementsClass.test()
        println("hello")
    }
}
