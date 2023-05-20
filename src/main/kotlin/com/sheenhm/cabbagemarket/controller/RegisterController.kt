package com.sheenhm.cabbagemarket.controller

import com.sheenhm.cabbagemarket.repository.MyInfo
import com.sheenhm.cabbagemarket.repository.MyInfoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RegisterController {
    @Autowired
    lateinit var myInfoRepository: MyInfoRepository

    @PostMapping("/register")
    fun registerUser(@RequestBody request: MyInfo): ResponseEntity<String> {
        val savedUserInfo = myInfoRepository.save(request)
        return ResponseEntity.ok("User registered successfully with ID: ${savedUserInfo.userId}")
    }
}