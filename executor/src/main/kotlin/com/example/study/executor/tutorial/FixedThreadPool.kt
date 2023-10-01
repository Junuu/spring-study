package com.example.study.executor.tutorial

import com.example.study.log.logger
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import java.lang.Thread.sleep
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors.*

//@Component
class FixedThreadPool : ApplicationRunner {
    override fun run(args: ApplicationArguments) {
        val executorService: ExecutorService = newFixedThreadPool(2)
        val myTaskProcessOneSeconds = {
            logger.info{"1초가 걸립니다."}
            sleep(1000)
        }
        //1초가 걸리는 작업을 3번 수행, 쓰레드풀의 크기는 2이다.
        //최소 2초가 걸려야 한다.
        executorService.submit(myTaskProcessOneSeconds)
        executorService.submit(myTaskProcessOneSeconds)
        executorService.submit(myTaskProcessOneSeconds)
    }
}
