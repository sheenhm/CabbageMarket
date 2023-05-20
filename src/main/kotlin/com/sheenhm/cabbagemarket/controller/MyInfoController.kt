package com.sheenhm.cabbagemarket.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.sheenhm.cabbagemarket.repository.MyInfo
import com.sheenhm.cabbagemarket.repository.MyInfoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView

@Controller
class MyInfoController {

    @Autowired
    lateinit var myInfoRepository: MyInfoRepository

    @GetMapping("/myinfo/{userId}")
    fun getMyInfo(@PathVariable userId: String, model: Model): String {
        val myInfo = myInfoRepository.findByUserId(userId)
        if (myInfo != null) {
            model.addAttribute("myInfo", myInfo)
            return "myinfo"
        }
        throw ChangeSetPersister.NotFoundException()
    }

    @GetMapping("/myinfo/{userId}/edit")
    fun showEditForm(@PathVariable userId: String, model: Model): String {
        val myInfo = myInfoRepository.findByUserId(userId)
        if (myInfo != null) {
            model.addAttribute("myInfo", myInfo)
            return "edit"
        }
        throw ChangeSetPersister.NotFoundException()
    }

    @PostMapping("/myinfo/{userId}/edit")
    fun updateMyInfo(@PathVariable userId: String, @ModelAttribute("myInfo") myInfo: MyInfo): String {
        val existingInfo = myInfoRepository.findByUserId(userId)
        if (existingInfo != null) {
            existingInfo.name = myInfo.name
            existingInfo.tel = myInfo.tel
            existingInfo.geoX = myInfo.geoX
            existingInfo.geoY = myInfo.geoY
            myInfoRepository.save(existingInfo)
            return "redirect:/myinfo/$userId"
        }
        throw ChangeSetPersister.NotFoundException()
    }
}