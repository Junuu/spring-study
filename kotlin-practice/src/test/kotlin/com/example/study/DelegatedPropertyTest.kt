package com.example.study

import org.junit.jupiter.api.Test
import kotlin.reflect.KProperty

class DelegatedPropertyTest {

    @Test
    fun `Lazy Property를 활용해보자`(){

    }

    @Test
    fun `Delegated Property를 직접 만들어보자`(){
        var token: String? by LoggingProperty(null)
        token = "hi"
        println("어떤 일이 벌어질까요?")
        val getToken = token
        //출력
        //token changed from null to hi
        //token returned value hi
    }
}


private class LoggingProperty<T>(var value: T){
    operator fun getValue(
            thisRef: Any?,
            prop: KProperty<*>,
    ): T{
        println("property [${prop.name}]의 조회가 감지되었습니다. 조회된 값[$value]")
        return value
    }

    operator fun setValue(
            thisRef: Any?,
            prop: KProperty<*>,
            newValue: T,
    ) {
        println("property [${prop.name}]의 변경이 감지되었습니다. 기존값[$value] 변경값[$newValue]")
        value = newValue
    }
}
