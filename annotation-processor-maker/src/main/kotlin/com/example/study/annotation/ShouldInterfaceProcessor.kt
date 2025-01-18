package com.example.study.annotation

import com.google.auto.service.AutoService
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.lang.model.element.Name
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic


@AutoService(Processor::class)
class ShouldInterfaceProcessor : AbstractProcessor() {
    override fun getSupportedAnnotationTypes(): Set<String> {
        return mutableSetOf(ShouldInterface::class.java.name)
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }

    override fun process(annotations: Set<TypeElement?>?, roundEnv: RoundEnvironment): Boolean {
        //ShouldInterface 이라는 애노테이션을 가지고있는 엘리먼츠를 가지고 온다.
        val elements: Set<Element> = roundEnv.getElementsAnnotatedWith(ShouldInterface::class.java)
        for (element in elements) {
            val elementName: Name = element.getSimpleName()
            if (element.getKind() !== ElementKind.INTERFACE) { // 엘리먼츠가 인터페이스가 아닐 경우 에러 출력
                processingEnv.messager.printMessage(
                    Diagnostic.Kind.ERROR,
                    "ShouldInterface annotation can not be used on $elementName"
                )
            } else {
                processingEnv.messager.printMessage(Diagnostic.Kind.NOTE, "ShouldInterface annotation Processing $elementName")
            }
        }
        return true
    }
}


