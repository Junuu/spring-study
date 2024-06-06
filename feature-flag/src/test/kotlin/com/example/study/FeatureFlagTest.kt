package com.example.study

import org.jeasy.random.EasyRandom
import org.jeasy.random.EasyRandomParameters
import org.jeasy.random.randomizers.range.LongRangeRandomizer
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class FeatureFlagTest{

    @Test
    fun `Feature Toggle의 개수는 10개 이하여야 한다`(){
        val toggleCount = FeatureFlag.Companion::class.java.declaredFields.size
        require(toggleCount < 10)
    }

    @Test
    fun `TestHelper 활용`(){
        val sut = TestHelper.tooManyFieldFixture(col3 = "테스트하고 싶은것 override")
        assertEquals(sut.col3, "테스트하고 싶은것 override")
    }

    @Test
    fun `easyRandom 활용`(){
        val easyRandom = EasyRandom()
        val sut: TooManyField = easyRandom.nextObject(TooManyField::class.java)
                .copy(col3 = "테스트하고 싶은것 override")

        assertEquals(sut.col3, "테스트하고 싶은것 override")
    }

    @Test
    fun `조금 더 편하게 easyRandom 활용`(){
        val sut = easyRandom<TooManyField>()
                .copy(col3 = "테스트하고 싶은것 override")

        assertEquals(sut.col3, "테스트하고 싶은것 override")
    }
}

data class TooManyField(
        val col1: String,
        val col2: String,
        val col3: String,
        // ...
        val col100: String,
)

object TestHelper{
    fun tooManyFieldFixture(
            col1: String = "col1",
            col2: String = "col2",
            col3: String = "col3",
            col100: String = "col100",
    ): TooManyField{
        return TooManyField(
                col1 = col1,
                col2 = col2,
                col3 = col3,
                col100 = col100,
        )
    }
}

val EASY_RANDOM = EasyRandomParameters()
        .collectionSizeRange(2,3)
        .randomize(Long::class.java, LongRangeRandomizer(1, 10000)) // Long 타입에 대하여 1~10000으로 범위 제약
        .run { EasyRandom(this) }

inline fun <reified T: Any> easyRandom(): T = EASY_RANDOM.nextObject(T::class.java)