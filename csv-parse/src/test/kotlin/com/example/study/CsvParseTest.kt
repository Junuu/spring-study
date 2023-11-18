package com.example.study

import com.opencsv.bean.CsvBindByName
import com.opencsv.bean.CsvToBean
import com.opencsv.bean.CsvToBeanBuilder
import org.junit.jupiter.api.Test
import java.io.Reader
import java.nio.file.Files
import java.nio.file.Paths


class CsvParseTest {

    @Test
    fun `csv 파일을 파싱한다`() {
        val filePath = "/Users/junuu/Downloads/study/csv-parse/src/test/kotlin/com/example/study/member.csv"

        val reader: Reader = Files.newBufferedReader(Paths.get(filePath))
        val csvToBean: CsvToBean<CsvModel> = CsvToBeanBuilder<CsvModel>(reader)
            .withType(CsvModel::class.java)
            .withIgnoreLeadingWhiteSpace(true)
            .build()

        val lists = csvToBean.parse()
        println(lists.size)
    }
}


data class CsvModel(
    @CsvBindByName(column = "your_column")
    private val ciIdk: String? = null,
)
