package com.example.study.service

import com.example.study.controller.request.DtoA
import com.example.study.controller.request.DtoB
import org.springframework.stereotype.Service

@Service("DtoA")
class DtoAServiceV3: SingleEndpointProcessor<DtoA> {
    override fun process(request: DtoA): String {
        return request.col1
    }
}

@Service("DtoB")
class DtoBServiceV3 : SingleEndpointProcessor<DtoB> {
    override fun process(request: DtoB): String {
        return request.col3
    }
}

