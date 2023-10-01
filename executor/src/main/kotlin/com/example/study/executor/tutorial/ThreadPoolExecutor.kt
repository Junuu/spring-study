package com.example.study.executor.tutorial

import com.example.study.log.logger
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import java.util.concurrent.*
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy

//@Component
class ThreadPoolExecutor: ApplicationRunner {

    override fun run(args: ApplicationArguments) {
        val executorService: ExecutorService = ThreadPoolExecutor(
            1,
            2,
            60L,
            TimeUnit.MILLISECONDS,
            LinkedBlockingQueue<Runnable>(1),
//            CallerRunsPolicy()
        )

//        executorService.awaitTermination(10L, TimeUnit.SECONDS)

        val myTaskProcessFiveSeconds = {
            Thread.sleep(5000)
            logger.info{"5초가 걸립니다."}
        }

        executorService.submit(myTaskProcessFiveSeconds)
        executorService.submit(myTaskProcessFiveSeconds)
        executorService.shutdown()

    }
}
