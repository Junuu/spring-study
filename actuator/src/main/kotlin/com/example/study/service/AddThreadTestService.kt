package com.example.study.service

import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class AddThreadTestService {

    @Async(value = "myExecutor")
    fun addAsyncThread(){
        //1000초 Sleep해라
        Thread.sleep(1000 * 1000)
    }
}


