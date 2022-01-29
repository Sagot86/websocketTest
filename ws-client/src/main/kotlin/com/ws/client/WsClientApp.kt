package com.ws.client

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class WsClientApp

fun main(args: Array<String>) {
    runApplication<WsClientApp>(*args)
}
