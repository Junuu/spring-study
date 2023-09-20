package com.example.study.batch.job.database

import com.example.study.application.jpa.TestStatus
import java.util.*

data class ReaderDto(
    val id: UUID,
    val status: TestStatus,
)
