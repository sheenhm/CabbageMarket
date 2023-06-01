package com.sheenhm.cabbagemarket.controller.product

import com.sheenhm.cabbagemarket.model.UsedInfo
import com.sheenhm.cabbagemarket.repository.ProductInfo
import com.sheenhm.cabbagemarket.repository.ProductRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

@Controller
class TradeController(private val productRepository: ProductRepository) {
    @GetMapping("/sell")
    fun showSellForm(model: Model): String {
        model.addAttribute("productInfo", UsedInfo("", "", "", 0))
        return "sell"
    }

    @PostMapping("/sell")
    fun sell(@ModelAttribute("usedInfo") usedInfo: UsedInfo): String {
        val productInfo = ProductInfo(
            productId = 0,
            title = usedInfo.title,
            sellerId = usedInfo.sellerId,
            imageUrl = usedInfo.imageUrl,
            price = usedInfo.price
        )
        productRepository.save(productInfo)
        return "redirect:/product"
    }
}