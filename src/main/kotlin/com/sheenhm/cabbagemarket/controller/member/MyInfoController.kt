package com.sheenhm.cabbagemarket.controller.member

import com.sheenhm.cabbagemarket.model.UserInfo
import com.sheenhm.cabbagemarket.repository.MyInfo
import com.sheenhm.cabbagemarket.repository.MyInfoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

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

    @ResponseBody
    @GetMapping("/myinfo2/{userId}")    // Json으로 넘겨받기
    fun getMyInfo2(@PathVariable userId: String, model: Model): MyInfo? {
        return myInfoRepository.findByUserId(userId)
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

    @PostMapping("/myinfo/{userId}/update")
    fun modifyMyInfo(@PathVariable userId: String, @ModelAttribute("myInfo") userInfo: UserInfo): String {
        val existingInfo = myInfoRepository.findByUserId(userId)
        if (existingInfo != null) {
            existingInfo.name = userInfo.name
            existingInfo.tel = userInfo.tel
            existingInfo.geoX = userInfo.geoX
            existingInfo.geoY = userInfo.geoY
            myInfoRepository.save(existingInfo)
            return "redirect:/myinfo/$userId"
        }
        throw ChangeSetPersister.NotFoundException()
    }

    @GetMapping("/myinfo/{userId}/delete")
    fun deleteMyInfo(@PathVariable userId: String): String {
        val existingInfo = myInfoRepository.findByUserId(userId)
        if (existingInfo != null) {
            myInfoRepository.delete(existingInfo)
            return "redirect:/deleted"
        }
        throw ChangeSetPersister.NotFoundException()
    }

    @GetMapping("/deleted")
    fun deleted(): String {
        return "deleted"
    }
}