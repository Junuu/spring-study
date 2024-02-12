package com.example.study.service

import com.example.study.controller.request.DtoA
import com.example.study.controller.request.DtoB
import org.springframework.stereotype.Service

@Service
class DtoAServiceV1{
    fun process(dtoA: DtoA): String{
        return dtoA.col1
    }
}

@Service
class DtoBServiceV1{
    fun process(dtoB: DtoB): String{
        return dtoB.col4
    }
}