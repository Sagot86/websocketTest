package com.ws.server.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenAPIConfig {
    @Bean
    fun myOpenAPI(): OpenAPI {
        val info: Info = Info()
            .title("WS Server")
            .version("1.0")
        return OpenAPI().info(info)
    }
}