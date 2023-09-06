package com.example.study.controller

import org.apache.poi.openxml4j.opc.OPCPackage
import org.apache.poi.openxml4j.opc.PackageAccess
import org.apache.poi.poifs.crypt.EncryptionInfo
import org.apache.poi.poifs.crypt.EncryptionMode
import org.apache.poi.poifs.filesystem.POIFSFileSystem
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.io.File
import java.io.FileOutputStream


@RestController
class ExcelTestController {

    @GetMapping("/generate-excel-file")
    fun `엑셀파일을 생성합니다`(): String{

        val workbook = XSSFWorkbook()
        val sheet: XSSFSheet = workbook.createSheet("User Info")
        // Data for excel
        val bookData = arrayOf(
            arrayOf("Name", "Department", "email"),
            arrayOf("Aman Ullah", "EEE", "aman@stsvinc.com"),
            arrayOf("Ismail Hossain", "EEE", "ismailhossain@example.com"),
            arrayOf("Alam al soud Ratul", "CSE", "alamalsaud@example.com"),
            arrayOf("Md. Nasir uddin", "CSE", "nasir@stsvinc.com")
        )

        // Adding the data to the excel worksheet
        var rowCount = 0
        for (aBook in bookData) {
            val row: Row = sheet.createRow(rowCount++)

            var columnCount = 0

            for (field in aBook) {
                val cell: Cell = row.createCell(columnCount++)
                if (field is String) {
                    cell.setCellValue(field)
                }
            }
        }

        // Save the excel file in a directory
        FileOutputStream("file.xlsx").use { outputStream ->
            workbook.write(outputStream)
        }

        //Protecting the file with password
        try {
            POIFSFileSystem().use { fs ->
                val info =
                    EncryptionInfo(EncryptionMode.agile)
                val enc = info.encryptor
                enc.confirmPassword("satl")
                OPCPackage.open(File("file.xlsx"), PackageAccess.READ_WRITE).use { opc ->
                    enc.getDataStream(fs).use { os ->
                        opc.save(
                            os
                        )
                    }
                }
                FileOutputStream("file.xlsx").use { fos -> fs.writeFilesystem(fos) }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }


        return "생성 완료"
    }
}
