package com.sheenhm.cabbagemarket.controller

import org.springframework.web.bind.annotation.*

@RestController
class SellController {
    data class UsedItem(
        val usedId: String,
        val title: String,
        val imageUrl: String,
        val price: String,
        val sellerId: String
    )

    private val usedItems = mutableListOf<UsedItem>()

    @GetMapping("/getUsed/user/{userId}")
    fun getUsedByUser(@PathVariable userId: String): List<UsedItem> {
        return usedItems.filter { it.sellerId == userId }
    }

    @GetMapping("/getUsed/used/{usedId}")
    fun getUsedByItem(@PathVariable usedId: String): UsedItem? {
        return usedItems.find { it.usedId == usedId }
    }

    @PostMapping("/makeUsed/{userId}")
    fun makeUsed(
        @PathVariable userId: String,
        @RequestBody usedItem: UsedItem
    ): Map<String, String> {
        usedItems.add(usedItem.copy(usedId = (usedItems.size + 1).toString(), sellerId = userId))
        return mapOf("status" to "ok")
    }
}
