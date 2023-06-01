package com.sheenhm.cabbagemarket.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class IndexController {
    @GetMapping("/")
    fun redirectToWelcome(): String {
        return "redirect:/welcome"
    }

    @GetMapping("/welcome")
    fun welcome(): String {
        return "welcome"
    }
}