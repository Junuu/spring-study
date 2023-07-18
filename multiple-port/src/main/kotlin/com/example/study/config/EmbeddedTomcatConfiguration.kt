package com.example.study.config

import org.apache.catalina.connector.Connector
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.servlet.server.ServletWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class EmbeddedTomcatConfiguration {

    @Bean
    fun servletContainer(): ServletWebServerFactory {
        val tomcat = TomcatServletWebServerFactory()
        tomcat.addAdditionalTomcatConnectors(
            createStandardConnector(8081),
            createStandardConnector(8082),
            createStandardConnector(8083),
        )

        return tomcat
    }

    private fun createStandardConnector(portNumber: Int): Connector {
        val connector = Connector("org.apache.coyote.http11.Http11NioProtocol")
        connector.port = portNumber
        return connector
    }
}
