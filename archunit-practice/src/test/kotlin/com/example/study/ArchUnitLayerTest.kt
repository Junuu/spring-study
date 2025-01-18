package com.example.study

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition
import org.junit.jupiter.api.Test

class ArchitectureTest {

    private val importedClasses: JavaClasses = ClassFileImporter()
        .importPackages("com.example.study") // 자신의 루트 패키지로 변경

    @Test
    fun `controllers should not depend on repositories`() {
        val rule = ArchRuleDefinition.noClasses()
            .that().resideInAPackage("..controller..")
            .should().dependOnClassesThat()
            .resideInAPackage("..repository..")
            .because("Controllers should not depend directly on repositories")

        rule.check(importedClasses)
    }

}
