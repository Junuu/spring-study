package com.example.study

import org.junit.jupiter.api.Test

class StringFullTextHalfTextExtensionTest {
    @Test
    fun `반각 문자 전각 문자 변환 테스트`(){
        val english = "How does English translate to half-width characters?"
        println("전각 문자로 변환시 ${english.toFullWidthCharacter()}")
        println("반각 문자로 변환시 ${english.toHalfWidthCharacter()}")

        val koreanText = "한글은 전각문자 인데 어떻게 변환될까요?"
        println("전각 문자로 변환시 ${koreanText.toFullWidthCharacter()}")
        println("반각 문자로 변환시 ${koreanText.toHalfWidthCharacter()}")
    }
}