package com.sheenhm.cabbagemarket.controller

import org.springframework.web.bind.annotation.*

@RestController
class MyInfoController {
    data class MyInfo(
        val orderList: List<Int>,
        val geo: Geo,
        val name: String,
        val tel: String
    )

    data class Geo(
        val x: Double,
        val y: Double
    )

    @GetMapping("/myinfo/{userId}")
    fun getMyInfo(@PathVariable userId: String): MyInfo {
        return MyInfo(
            listOf(1, 2, 3, 4),
            Geo(128.028737474, 38.2393462873),
            "",
            ""
        )
    }
}