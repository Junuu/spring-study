package com.example.study.service

import com.example.study.controller.request.DtoAV2
import com.example.study.controller.request.DtoBV2
import com.example.study.controller.request.SingleEndPointRequestWithCustomDeserializer
import org.springframework.stereotype.Service

@Service
class DtoServiceV2: SingleEndpointProcessorV2 {
    override fun process(request: SingleEndPointRequestWithCustomDeserializer): String {
        val dtoB = request as DtoBV2
        println(dtoB)
        return dtoB.col5
    }
    override fun isSupport(type: String): Boolean {
        return type == "DtoB"
    }
}

@Service
class DtoAServiceV2: SingleEndpointProcessorV2 {
    override fun process(request: SingleEndPointRequestWithCustomDeserializer): String {
        val dtoA = request as DtoAV2
        println(dtoA)
        return dtoA.col1
    }

    override fun isSupport(type: String): Boolean {
        return type == "DtoA"
    }
}