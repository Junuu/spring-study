package com.example.study.controller.request

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode

interface SingleEndPointRequestWithCustomDeserializer{
    val type: String
}

data class DtoAV2(
        val col1: String,
        val col2: String,
        override val type: String = "DtoA",
): SingleEndPointRequestWithCustomDeserializer

data class DtoBV2(
        val col3: String,
        val col4: String,
        val col5: String,
        override val type: String = "DtoB",
): SingleEndPointRequestWithCustomDeserializer


class MyCustomDeserializer : JsonDeserializer<SingleEndPointRequestWithCustomDeserializer>() {
    override fun deserialize(jsonParser: JsonParser, deserializationContext: DeserializationContext): SingleEndPointRequestWithCustomDeserializer {

        val rootNode: JsonNode = jsonParser.codec.readTree(jsonParser)
        val typeNode = rootNode.get("type") ?: throw IllegalArgumentException("type이 존재하지 않습니다")
        val type = typeNode.asText()
        return when(type){
            "DtoA" -> DtoAV2(col1 = rootNode.get("col1").asText(), col2 = rootNode.get("col2").asText(), type = "DtoA")
            "DtoB" -> DtoBV2(col3 = rootNode.get("col3").asText(), col4 = rootNode.get("col4").asText(), col5  = rootNode.get("col5").asText(), type = "DtoB")
            else -> throw IllegalArgumentException("지원하지 않는 type: $type")
        }
    }
}