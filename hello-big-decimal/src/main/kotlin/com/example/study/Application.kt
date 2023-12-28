package com.example.study

import com.example.study.BigDecimalUtils.Companion.HALF_UP_ROUNDING_MODE
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.math.RoundingMode


@SpringBootApplication
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}

@Component
class BigDecimalTutorial: ApplicationRunner {
	override fun run(args: ApplicationArguments?) {
		val doubleValue: Double = 0.3
		val bigDecimalByDoubleValue = BigDecimal(doubleValue)
		println(bigDecimalByDoubleValue)

		val bigDecimalByString = BigDecimal("0.3")
		println(bigDecimalByString)

		println("------------------------------------")

		val seven = BigDecimal("7")
		val three = BigDecimal("3")

		// a.add(b)와 동일
		// 10
		println(seven + three)

		// a.subtract(b)와 동일
		// 4
		println(seven - three)

		// a.multiply(b)와 동일
		// 21
		println(seven * three)

		// a.divide(b, RoundingMode.HALF_EVEN)와 동일
		// 2
		println(seven / three)

		// Java와 동일한 방법으로 메서드를 호출할 수도 있다
		// 2.3
		println(seven.divide(three, 1, RoundingMode.HALF_UP))

		println("------------------------------------")

		val one = BigDecimal("1")
		println(one.div(three))

		println("------------------------------------")
		val zeroWithDecimalPoint = BigDecimal("0.000")
		val zeroBigDecimal = BigDecimal.ZERO

		println(zeroWithDecimalPoint == zeroBigDecimal)
		println(zeroBigDecimal.compareTo(zeroBigDecimal))

	}
}

operator fun BigDecimal.div(other: BigDecimal): BigDecimal = this.divide(other, HALF_UP_ROUNDING_MODE)

class BigDecimalUtils {

	companion object {
		val HALF_UP_ROUNDING_MODE = RoundingMode.HALF_UP
	}
}
