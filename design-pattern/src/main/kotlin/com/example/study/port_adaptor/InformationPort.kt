package com.example.study.port_adaptor

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

interface InformationPort {

    fun getInformation(): String

    @Component
    @Profile("local")
    class DummyInfomationAdpator: InformationPort {
        override fun getInformation(): String {
            return "DUMMY INFORMATION"
        }
    }

    @Component
    @Profile("!local")
    class DefaultInformationAdaptor(
            private val feignClient: ExternalServiceCallFeignClient
    ): InformationPort{
        override fun getInformation(): String{
            return feignClient.getExternalInformation()
        }
    }
}