package com.ws.client.service

import com.ws.client.model.InputMessage
import com.ws.client.model.OutputMessage
import jakarta.annotation.PostConstruct
import org.springframework.messaging.converter.MappingJackson2MessageConverter
import org.springframework.messaging.simp.stomp.*
import org.springframework.stereotype.Service
import org.springframework.web.socket.client.WebSocketClient
import org.springframework.web.socket.client.standard.StandardWebSocketClient
import org.springframework.web.socket.messaging.WebSocketStompClient
import com.ws.client.util.getLogger
import java.lang.reflect.Type

@Service
class WsService(
) {

    private lateinit var wsSession: StompSession

    @PostConstruct
    fun init() {
        val webSocketClient: WebSocketClient = StandardWebSocketClient()
        val stompClient = WebSocketStompClient(webSocketClient)
        stompClient.messageConverter = MappingJackson2MessageConverter()

        val url = "ws://127.0.0.1:8066/ws-server-test"
        val sessionHandler: StompSessionHandler = MyStompSessionHandler()
        wsSession = stompClient.connectAsync(url, sessionHandler).get()
        wsSession.subscribe("/topic/greetings", sessionHandler)


    }

    fun sendSomeShit(payload: String) {
        wsSession.send("/app/hello", InputMessage(payload))
    }

}

class MyStompSessionHandler : StompSessionHandlerAdapter() {

    override fun getPayloadType(headers: StompHeaders): Type {
        return OutputMessage::class.java
    }

    override fun handleFrame(headers: StompHeaders, payload: Any?) {
        log.info("WE GOT FUCKING PAYLOAD ${(payload as OutputMessage).content}")
    }

    override fun afterConnected(session: StompSession, connectedHeaders: StompHeaders) {
        log.info("Connected to WebSocket")
    }

    override fun handleException(session: StompSession, command: StompCommand, headers: StompHeaders, payload: ByteArray, exception: Throwable) {
        log.error("Exception occurred: " + exception.message)
    }

    override fun handleTransportError(session: StompSession, exception: Throwable) {
        log.error("Transport error occurred: " + exception.message)
    }

    companion object {
        private val log = getLogger()
    }
}