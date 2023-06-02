package com.sheenhm.cabbagemarket.controller.product

import com.sheenhm.cabbagemarket.model.UsedInfo
import com.sheenhm.cabbagemarket.repository.ProductInfo
import com.sheenhm.cabbagemarket.repository.ProductRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

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
            price = usedInfo.price,
            issold = 0
        )
        productRepository.save(productInfo)
        return "redirect:/market"
    }

    @GetMapping("/buy")
    fun showBuyForm(): String {
        return "buy"
    }

    @PostMapping("/buy")
    fun buy(@RequestParam("title") title: String, model: Model): String {
        val product = productRepository.findByTitle(title)
        if (product != null) {
            if (product.issold == 0) {
                product.issold = 1
                productRepository.save(product)
                model.addAttribute("error", "상품 구매가 완료되었습니다.")
            } else {
                model.addAttribute("error", "이미 판매된 상품입니다.")
            }
        } else {
            model.addAttribute("error", "존재하지 않는 상품입니다.")
        }
        return "buy"
    }
}