package com.example.study.service

import com.example.study.controller.request.DtoA
import com.example.study.controller.request.DtoB
import org.springframework.stereotype.Component

@Component
class SingleEndpointDispatcherV1(
        private val dtoAService: DtoAServiceV1,
        private val dtoBService: DtoBServiceV1,
){
    fun process(request: Map<String, String>){
        val requestType = request["type"] ?: throw IllegalArgumentException("type 값은 필수적으로 들어와야 합니다.")
        when(requestType){
            "DtoA" -> { dtoAService.process(ConverterV1.requestToDtoA(request))}
            "DtoB" -> { dtoBService.process(ConverterV1.requestToDtoB(request))}
            else -> throw IllegalArgumentException("지원하지 않는 type: $requestType")
        }
    }
}

object ConverterV1{
    fun requestToDtoA(request: Map<String,String>): DtoA {
        return DtoA(
                col1 = request["col1"]!!,
                col2 = request["col2"]!!,
        )
    }

    fun requestToDtoB(request: Map<String,String>): DtoB {
        return DtoB(
                col3 = request["col3"]!!,
                col4 = request["col4"]!!,
                col5 = request["col5"]!!,
        )
    }
}