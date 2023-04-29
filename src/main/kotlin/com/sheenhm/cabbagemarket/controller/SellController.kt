package com.sheenhm.cabbagemarket.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class SellController {
    data class UsedItem(
        val usedId: String,
        val title: String,
        val imageUrl: String,
        val price: String,
        val sellerId: String
    )

    @GetMapping("/getUsed/{userId}")
    fun getUsed(@PathVariable userId: String): List<UsedItem> {
        return listOf(
            UsedItem(
                "1",
                "ps4pro 팝니다.",
                "http://gdimg.gmarket.co.kr/2360305459/still/280?ver=1651648538",
                "300000",
                "sasimi"
            ),
            UsedItem(
                "2",
                "ps3 팝니다.",
                "http://gdimg.gmarket.co.kr/2360305459/still/280?ver=1651648538",
                "100000",
                "sasimi"
            ),
            UsedItem(
                "3",
                "ps2 팝니다.",
                "http://gdimg.gmarket.co.kr/2360305459/still/280?ver=1651648538",
                "20000",
                "sasimi"
            )
        )
    }
}
