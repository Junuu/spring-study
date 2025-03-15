package com.example.study.util

object MetricUtils {

    inline fun <T> measureTimeMillis(block: () -> T): Pair<T, Long> {
        val start = System.nanoTime()
        val result = block()
        val end = System.nanoTime()
        return result to (end - start) / 1_000_000 // 밀리초 변환
    }

}