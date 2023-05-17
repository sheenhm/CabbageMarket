package com.sheenhm.cabbagemarket.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.sheenhm.cabbagemarket.repository.MyInfo
import com.sheenhm.cabbagemarket.repository.MyInfoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class MyInfoController {

    @Autowired
    lateinit var myInfoRepository: MyInfoRepository

    @GetMapping("/myinfo/{userId}")
    fun getMyInfo(@PathVariable userId: String): ResponseEntity<String> {
        val myInfo = myInfoRepository.findByUserId(userId)
        return myInfo?.let {
            val objectMapper = ObjectMapper()
            val myInfoJson = objectMapper.writeValueAsString(it)
            ResponseEntity.ok(myInfoJson)
        } ?: ResponseEntity.notFound().build()
    }

    @PutMapping("/myinfo/{userId}/edit")
    fun editMyInfo(@PathVariable userId: String, @RequestBody request: MyInfo): ResponseEntity<String> {
        val existingInfo = myInfoRepository.findById(userId)
        return if (existingInfo.isPresent) {
            val updatedInfo = request.copy(userId = userId)
            myInfoRepository.save(updatedInfo)
            ResponseEntity.ok("{\"status\": \"ok\"}")
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found")
        }
    }
}