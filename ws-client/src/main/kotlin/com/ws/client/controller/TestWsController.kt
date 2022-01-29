package com.ws.client.controller

import com.ws.client.service.WsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/simple")
class TestWsController(private val wsService: WsService) {

    @GetMapping("/test")
    fun getInfo(@RequestParam payload: String) = wsService.sendSomeShit(payload)

}