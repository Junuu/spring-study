package com.example.study

import com.example.study.annotation.AllowedClientUsingEnumReturnType
import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.domain.JavaClass
import com.tngtech.archunit.core.domain.JavaField
import com.tngtech.archunit.core.domain.JavaMethod
import com.tngtech.archunit.core.domain.JavaParameterizedType
import com.tngtech.archunit.core.domain.JavaType
import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.lang.ArchCondition
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition
import com.tngtech.archunit.lang.ConditionEvents
import com.tngtech.archunit.lang.SimpleConditionEvent
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.cloud.openfeign.FeignClient

class FeignClientEnumTest {

    // 테스트할 클래스를 임포트
    val importedClasses: JavaClasses = ClassFileImporter()
        .withImportOption(ImportOption.DoNotIncludeTests())
        .importPackages(FeignTestApplication::class.java.packageName)

    @Test
    @Disabled
    @DisplayName("step1 - feign interface 대상 가져오기")
    fun step1() {
        val rule = ArchRuleDefinition
            .methods()
            .that()
            .areDeclaredInClassesThat()
            .areAnnotatedWith(FeignClient::class.java)
            .should(
                object : ArchCondition<JavaMethod>("feign method 반환값 가져오기") {
                    override fun check(method: JavaMethod, event: ConditionEvents) {
                        val fullName: String = method.fullName
                        val returnType: JavaType = method.returnType
                        println(fullName)
                        println(returnType)
                    }
                }
            )
        rule.check(importedClasses)
    }

    @Test
    @Disabled
    @DisplayName("step2 - method 반환값 enum 인지 검사하기")
    fun step2() {
        val rule = ArchRuleDefinition
            .methods()
            .that()
            .areDeclaredInClassesThat()
            .areAnnotatedWith(FeignClient::class.java)
            .should(
                object : ArchCondition<JavaMethod>("feign method 반환값 가져오기") {
                    override fun check(method: JavaMethod, events: ConditionEvents) {
                        val returnType: JavaType = method.returnType
                        val returnTypeClass: JavaClass = returnType.toErasure()

                        if(returnTypeClass.isEnum){
                            events.add(
                                SimpleConditionEvent.violated(
                                    method,
                                    "Feign 메서드 ${method.fullName}에서 enum을 직접 리턴시키고 있습니다."
                                )
                            )
                            return
                        }
                    }
                }
            )
        rule.check(importedClasses)
    }

    @Test
    @Disabled
    @DisplayName("step3 - 제네릭의 내부에서 Enum을 활용하는 경우 검사하기")
    fun step3() {
        val rule = ArchRuleDefinition
            .methods()
            .that()
            .areDeclaredInClassesThat()
            .areAnnotatedWith(FeignClient::class.java)
            .should(
                object : ArchCondition<JavaMethod>("feign method 반환값 가져오기") {
                    override fun check(method: JavaMethod, events: ConditionEvents) {
                        val returnType: JavaType = method.returnType
                        val returnTypeClass: JavaClass = returnType.toErasure()


                        if(returnType is JavaParameterizedType){
                        // actualTypeArguments를 활용하면 List<T> 등의 구체타입을 가져올 수 있다.
                            val actualTypeArguments: List<JavaType> = returnType.actualTypeArguments
                            // Map<K,V> 인 경우에는 List의 size가 2개라서 순회해야 한다.
                            actualTypeArguments.forEach { javaType ->
                                if(javaType.toErasure().isEnum){
                                    events.add(
                                        SimpleConditionEvent.violated(
                                            method,
                                            "Feign 메서드 ${method.fullName}에서 enum을 직접 리턴시키고 있습니다."
                                        )
                                    )
                                    return
                                }
                            }
                        }

                        if(returnTypeClass.isEnum){
                            events.add(
                                SimpleConditionEvent.violated(
                                    method,
                                    "Feign 메서드 ${method.fullName}에서 enum을 직접 리턴시키고 있습니다."
                                )
                            )
                            return
                        }
                    }
                }
            )
        rule.check(importedClasses)
    }

    @Test
    @DisplayName("step4 - Nested Class 검사하기")
    fun step4() {
        val rule = ArchRuleDefinition
            .methods()
            .that()
            .areDeclaredInClassesThat()
            .areAnnotatedWith(FeignClient::class.java)
            .and()
            .areNotAnnotatedWith(AllowedClientUsingEnumReturnType::class.java)
            .should(
                object : ArchCondition<JavaMethod>("feign method 반환값 가져오기") {
                    override fun check(method: JavaMethod, events: ConditionEvents) {
                        val returnType: JavaType = method.returnType
                        val actualTypes = getActualTypes(returnType)

                        if(isUsingEnum(method = method, actualTypeArguments = actualTypes, events = events)){
                            return
                        }

                        checkFieldsRecursively(actualTypes, events){ fieldsActualTypes: List<JavaType>, events: ConditionEvents  ->

                            if(isUsingEnum(method = method, actualTypeArguments = fieldsActualTypes, events = events)){
                                return@checkFieldsRecursively true
                            }
                            return@checkFieldsRecursively false
                        }
                    }
                }
            )
        rule.check(importedClasses)
    }

    /**
     * @param javaType 실제 메서드의 반환타입
     * @return Map<K,V> 인 경우에는 List의 size가 2로 반환된다.
     */
    private fun getActualTypes(javaType: JavaType): List<JavaType> {
        return when(javaType){
            is JavaClass -> listOf(javaType)
            // actualTypeArguments를 활용하면 List<T> 등의 구체타입을 가져올 수 있다.
            // flatMap 활용하는 이유는 Map<String, Map<String, Enum>> 등의 중첩 제네릭이 활용될때 재귀적으로 호출하면서 모든 구체클래스를 들고오기 위함이다.
            is JavaParameterizedType -> javaType.actualTypeArguments.flatMap { getActualTypes(it) }
            else -> throw UnsupportedOperationException("지원하지 않는 타입입니다.")
        }
    }

    private fun isUsingEnum(method: JavaMethod, actualTypeArguments: List<JavaType>, events: ConditionEvents) : Boolean{
        actualTypeArguments.forEach { javaType: JavaType ->
            if(javaType.toErasure().isEnum){
                events.add(
                    SimpleConditionEvent.violated(
                        method,
                        "Feign 메서드 ${method.fullName}에서 enum을 직접 리턴시키고 있습니다."
                    )
                )
                return true
            }
        }
        return false
    }

    private fun checkFieldsRecursively(
        actualTypeArguments: List<JavaType>,
        events: ConditionEvents,
        visitedTypes: MutableSet<JavaType> = mutableSetOf(),
        block: (actualTypes: List<JavaType> , ConditionEvents) -> Boolean,
    ){
        actualTypeArguments.forEach {javaType: JavaType ->
            if(!visitedTypes.add(javaType)){
                return
            }

            val dtoClass: JavaClass = javaType.toErasure()

            dtoClass.fields.forEach { filed: JavaField ->
                val actualTypes: List<JavaType> = getActualTypes(filed.type)
                if(block(actualTypes, events)) return

                val fieldJavaClass: JavaClass = filed.rawType
                if(isMyClass(fieldJavaClass)){
                    checkFieldsRecursively(actualTypes, events, visitedTypes, block)
                }
            }
        }
    }

    private fun isMyClass(javaType: JavaType): Boolean = javaType.name.startsWith("com.example.study")


    @Test
    @Disabled
    fun `@FeignClient methods should not return types that are enums`() {
        val rule = ArchRuleDefinition
            .classes()
            .that()
            .areAnnotatedWith(FeignClient::class.java) // @FeignClient 어노테이션이 붙은 메소드
            .should(
                object : ArchCondition<JavaClass> ("checkEnum") {
                    override fun check(clazz: JavaClass, events: ConditionEvents) {
                        // 특정 클래스에 붙어있는 메서드들 검사
                        clazz.methods.forEach { method ->

                            if(method.isAnnotatedWith(AllowedClientUsingEnumReturnType::class.java)) {
                                return@forEach
                            }

                            val returnType: JavaType = method.returnType
                            if(isEnum(returnType)){
                                events.add(
                                    SimpleConditionEvent.violated(
                                        method,
                                        "Feign 메서드 ${method.fullName}에서 enum을 직접 리턴시키고 있습니다."
                                    )
                                )
                                return
                            }

                            checkFieldsRecursively(returnType.toErasure(), events) { dtoClass, field, event ->
                                if(isEnum(field.rawType)){
                                    events.add(
                                        SimpleConditionEvent.violated(
                                            method,
                                            "Feign 메서드 ${method.fullName}에서 enum을 직접 리턴시키고 있습니다."
                                        )
                                    )
                                    true
                                } else {
                                    false
                                }
                            }
                        }
                    }

                }
            )
        rule.check(importedClasses)
    }

    private fun isEnum(returnType: JavaType): Boolean {
        return returnType.toErasure().isEnum
    }

    private fun checkFieldsRecursively(
        clazz: JavaClass,
        events: ConditionEvents,
        onViolation: (clazz: JavaClass, field: JavaField, events: ConditionEvents) -> Boolean
    ) {
        // 중첩 클래스의 모든 필드 검사
        for (field in clazz.fields) {
            if (onViolation(clazz, field, events)) {
                return // 위반이 발생하면 중단
            }
            val fieldType = field.rawType

            // 일반적인 필드 검사
            if (!fieldType.isPrimitive && !fieldType.isEnum) {
                checkFieldsRecursively(fieldType, events, onViolation)
            }
        }
    } }
