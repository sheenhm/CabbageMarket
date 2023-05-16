package com.sheenhm.cabbagemarket.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class IndexController {
    @GetMapping("/hi")
    fun niceToMeetYou(): String {
        return "greeting"
    }
}