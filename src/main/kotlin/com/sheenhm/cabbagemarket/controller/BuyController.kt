package com.sheenhm.cabbagemarket.controller

import org.springframework.web.bind.annotation.*

@RestController
class BuyController {
    @GetMapping("/buy/{userId}/{usedId}")
    fun buy(@PathVariable userId: String, @PathVariable usedID: String): Map<String, String> {
        return mapOf("status" to "ok")
    }
}