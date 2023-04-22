package com.sheenhm.cabbagemarket.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class BuyController {
    @GetMapping("/buy/{userId}/{usedId}")
    fun buy(@PathVariable userId: String, @PathVariable usedId: String): Map<String, String> {
        return mapOf("status" to "ok")
    }
}