package com.sheenhm.cabbagemarket.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class MyInfoController {
    data class MyInfo(
        val userId: String,
        val orderList: List<Int>,
        val geo: Geo,
        val name: String,
        val tel: String
    )

    data class Geo(
        val x: Double,
        val y: Double
    )

    private val myInfo = mutableListOf<MyInfo>()

    @GetMapping("/myinfo/{userId}")
    fun getMyInfo(@PathVariable userId: String): MyInfo? {
        return myInfo.find { it.userId == userId }
    }

    @PutMapping("/myinfo/{userId}/edit")
    fun editMyInfo(@PathVariable userId: String, @RequestBody request: MyInfo): ResponseEntity<String> {
        val existingInfo = myInfo.find { it.userId == userId }
            ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found")

        val updatedInfo = MyInfo(
            userId = userId,
            orderList = existingInfo.orderList,
            geo = Geo(request.geo.x, request.geo.y),
            name = request.name,
            tel = request.tel
        )

        myInfo.remove(existingInfo)
        myInfo.add(updatedInfo)

        return ResponseEntity.ok().body("{\"status\": \"ok\"}")
    }
}