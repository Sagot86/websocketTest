package com.ws.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class WsServerApp

fun main(args: Array<String>) {
    runApplication<WsServerApp>(*args)
}
