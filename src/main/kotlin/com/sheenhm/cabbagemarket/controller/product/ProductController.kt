package com.sheenhm.cabbagemarket.controller.product

import com.sheenhm.cabbagemarket.model.UsedInfo
import com.sheenhm.cabbagemarket.repository.ProductInfo
import com.sheenhm.cabbagemarket.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class ProductController {

    @Autowired
    lateinit var productRepository: ProductRepository

    @ResponseBody
    @GetMapping("/product")
    fun getAll(): List<ProductInfo> {
        return productRepository.findAll().toList()
    }

    @ResponseBody
    @GetMapping("/product/seller/{sellerId}")
    fun getBySellerId(@PathVariable sellerId: String, model: Model): ProductInfo? {
        return productRepository.findBySellerId(sellerId)
    }
    @ResponseBody
    @GetMapping("/product/product/{productId}")
    fun getByProductId(@PathVariable productId: Int, model: Model): ProductInfo? {
        return productRepository.findByProductId(productId)
    }

    @GetMapping("/product/product/{productId}/delete")
    fun deleteProduct(@PathVariable productId: Int): String {
        val existingProduct = productRepository.findByProductId(productId)
        if (existingProduct != null) {
            productRepository.delete(existingProduct)
            return "redirect:/deleted"
        }
        throw ChangeSetPersister.NotFoundException()
    }
}