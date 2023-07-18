package com.example.study.service

import com.example.study.log.logger
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class KafkaConsumerService {

    fun messageOccurredException(){
        logger.info{ "KafkaConsumerService Start"}
        throw RuntimeException()
    }
}
