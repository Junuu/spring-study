package com.example.study.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.util.concurrent.Executors


@RestController
class TestController {

    @GetMapping("/sse")
    fun streamSseMvc(): SseEmitter {
        val emitter = SseEmitter()
        val sseMvcExecutor = Executors.newSingleThreadExecutor()
        sseMvcExecutor.execute {
            try {
                repeat(20){
                    val event = SseEmitter.event()
                        .data(System.currentTimeMillis())
                    emitter.send(event)
                    Thread.sleep(1000)
                }
            } catch (ex: Exception) {
                emitter.completeWithError(ex)
            }
        }
        return emitter
    }

}
