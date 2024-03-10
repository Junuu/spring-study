package com.example.study.controller

import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component


@Component
class LogbackTest: ApplicationRunner{
    override fun run(args: ApplicationArguments?) {
        logger.info("Example log from {}", LogbackTest::class.java.getSimpleName())
        loggerByStringName.info("test")
        fileLogger.info("it is a file logger")
    }
}

private val logger = LoggerFactory.getLogger(LogbackTest::class.java)
private val loggerByStringName = LoggerFactory.getLogger("root")
private val fileLogger = LoggerFactory.getLogger("com.baeldung.logback.tests")

