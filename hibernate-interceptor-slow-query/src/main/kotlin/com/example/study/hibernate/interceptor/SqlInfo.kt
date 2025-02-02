package com.example.study.hibernate.interceptor

import org.hibernate.resource.jdbc.spi.StatementInspector

class SQLTrackingStatementInspector: StatementInspector {
    override fun inspect(sql: String): String {
        return "/* inspect test */ $sql"
    }
}