package com.example.study.test

import com.example.study.SynchronizedCounter
import com.github.f4b6a3.tsid.TsidCreator
import com.github.f4b6a3.tsid.TsidFactory
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.Instant
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors


class IdGenerateTest {

    @Test
    fun `kotlin Synchronized 테스트해보기`(){
        val counter = SynchronizedCounter()
        val concurrentRequest = 1000
        val multipleRepeat = 100
        val threadPools = Executors.newFixedThreadPool(concurrentRequest)

        val latch = CountDownLatch(concurrentRequest * multipleRepeat)

        repeat(concurrentRequest * multipleRepeat) {
            threadPools.submit {
                try {
                    counter.increaseCount()
                } finally {
                    latch.countDown()
                }
            }
        }
        latch.await()

        Assertions.assertEquals(counter.getCurrentCount(), concurrentRequest * multipleRepeat)
    }

    @Test
    fun `id를 생성하면 어떻게 될까?`(){
        val string = TsidCreator.getTsid().toString() // 01226N0640J7Q
        val number = TsidCreator.getTsid().toLong() // 38352658567418872
        val tsid = TsidCreator.getTsid()
//        7079959520029446144
//        1828991921882937741
//        504933632984808831
        println(string)
        println(number)
        println(tsid)

        val instant = Instant.parse("1970-01-01T00:00:00.000Z")
        val withCustomEpoch = TsidFactory.builder()
            .withCustomEpoch(instant)
            .build()

        val custom = withCustomEpoch.create().toLong()
        println(custom)
        //2020년 시작기준 504933632984808831
        //2010년 시작기준 1828991921882937741
        //1970년 시작기준 7123478883931628827
        //현재쓰는 id값   7079959520029446144
        //50년정도 뒤에 문제가 생길것같으니 쓰는게 어떨까
        //혹시나 중복값이 생기면 어떻게하지? id를 새로만들어서 저장? 예외처리..

        //이제 이걸쓰면 pod를 여러개 띄워서 성능테스트 돌릴때 k8s 환경에서 충돌이 발생하는가?
        //문서기반으로 만들어보고, 테스트로 병렬 테스트 돌리고, pod 3개띄우고 성능테스트로 쭉 호출해봐기
    }
}
