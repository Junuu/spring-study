package com.example.study

import org.junit.jupiter.api.Test

class AdaptorPatternTest {

    /**
     * Adapter 가 구현하는 인터페이스
     * Client Interface : 클라이언트가 접근해서 사용할 고수준의 어댑터 모듈
     *
     */
    interface Port{
        fun operation()
    }

    /**
     * Adapter : Client 와 Adaptee(Service) 중간에서 호환성이 없는 둘을 연결시켜주는 역할을 담당.
     *
     */
    class Adaptor(
            private val adaptee: `제어할 수 없는 외부 클래스`,
    ): Port{
        override fun operation() {
            println(adaptee.specificOperation())
        }
    }

    /**
     * Adaptee : 클라이언트에서 사용하고 싶은 기존의 서비스 (하지만 호환이 안되서 바로 사용 불가능)
     */
    class `제어할 수 없는 외부 클래스`{
        fun specificOperation() = "특정 함수 호출"
    }

    @Test
    fun `Adaptor 패턴 활용해보기`(){
        val target: Port = Adaptor(`제어할 수 없는 외부 클래스`())
        target.operation()
    }
}