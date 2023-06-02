package com.sheenhm.cabbagemarket.controller.member

import com.sheenhm.cabbagemarket.model.UserInfo
import com.sheenhm.cabbagemarket.repository.MyInfo
import com.sheenhm.cabbagemarket.repository.MyInfoRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

@Controller
class RegisterController(private val myInfoRepository: MyInfoRepository) {

    @GetMapping("/register")
    fun showRegisterForm(model: Model): String {
        model.addAttribute("userInfo", UserInfo("", "", "", 0.0, 0.0))
        return "register"
    }

    @PostMapping("/register")
    fun register(@ModelAttribute("userInfo") userInfo: UserInfo, model: Model): String {
        val isUserIdExists = checkUserIdExists(userInfo.userId)
        if (isUserIdExists) {
            model.addAttribute("error", "이미 존재하는 ID입니다.")
            return "register"
        }
        val myInfo = MyInfo(
            id = 0,
            userId = userInfo.userId,
            geoX = userInfo.geoX,
            geoY = userInfo.geoY,
            name = userInfo.name,
            tel = userInfo.tel
        )
        myInfoRepository.save(myInfo)
        return "redirect:/welcome"
    }

    private fun checkUserIdExists(userId: String): Boolean {
        val existingInfo = myInfoRepository.findByUserId(userId)
        return existingInfo != null
    }
}