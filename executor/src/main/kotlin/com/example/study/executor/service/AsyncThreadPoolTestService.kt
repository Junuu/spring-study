package com.example.study.executor.service

import com.example.study.log.logger
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class AsyncThreadPoolTestService {

    @Async
    fun test(){
        logger.info{"stress 호출 시작"}
        Thread.sleep(7000)
        logger.info{"stress 호출 끝"}
    }
}

