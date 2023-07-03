package com.sheenhm.cabbagemarket.controller.member

import com.sheenhm.cabbagemarket.model.dto.UserInfo
import com.sheenhm.cabbagemarket.model.api.MyInfoResponse
import com.sheenhm.cabbagemarket.repository.MyInfoRepository
import io.swagger.v3.oas.annotations.Operation
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
    @Operation(description = "userId의 회원정보 조회")
    fun getMyInfo(@PathVariable userId: String, model: Model): String {
        val myInfo = myInfoRepository.findByUserId(userId)
        if (myInfo != null) {
            model.addAttribute("myInfo", myInfo)
            return "myinfo"
        }
        throw ChangeSetPersister.NotFoundException()
    }

    @ResponseBody
    @GetMapping("/myinfo2/{userId}")    // json으로 넘겨받기
    @Operation(description = "userId의 회원정보 조회 - json으로 반환")
    fun getMyInfo2(@PathVariable userId: String, model: Model): MyInfoResponse? {
        val myInfo = myInfoRepository.findByUserId(userId)
        return myInfo?.let { MyInfoResponse.myInfo2Response(it) }
    }

    @GetMapping("/myinfo/{userId}/edit")
    @Operation(description = "회원정보 수정 Form 보여주기")
    fun showEditForm(@PathVariable userId: String, model: Model): String {
        val myInfo = myInfoRepository.findByUserId(userId)
        if (myInfo != null) {
            model.addAttribute("myInfo", myInfo)
            return "edit"
        }
        throw ChangeSetPersister.NotFoundException()
    }

    @PostMapping("/myinfo/{userId}/update")
    @Operation(description = "회원정보 수정")
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
    @Operation(description = "회원정보 삭제")
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