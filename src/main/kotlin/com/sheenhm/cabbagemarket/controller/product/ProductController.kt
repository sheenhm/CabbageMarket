package com.sheenhm.cabbagemarket.controller.product

import com.sheenhm.cabbagemarket.model.api.ProductInfoResponse
import com.sheenhm.cabbagemarket.repository.ProductInfo
import com.sheenhm.cabbagemarket.repository.ProductRepository
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@Controller
class ProductController {

    @Autowired
    lateinit var productRepository: ProductRepository

    @ResponseBody
    @GetMapping("/market")
    @Operation(description = "전체 상품목록 조회")
    fun getAll(): List<ProductInfo> {
        return productRepository.findAll().toList()
    }

    @ResponseBody
    @GetMapping("/market/seller/{sellerId}")
    @Operation(description = "해당 seller가 판매 중인 상품목록 조회")
    fun getBySellerId(@PathVariable sellerId: String, model: Model): ResponseEntity<List<ProductInfoResponse>> {
        val products = productRepository.findBySellerId(sellerId)
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
        val response = products.map { ProductInfoResponse.productInfo2Response(it) }
        return ResponseEntity.ok(response)
    }

    /* ApiResponses 오류 해결해야 함, iterator 사용하지 않을 경우
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "상품 조회 성공",
            content = @Content(schema = @Schema(implementation = List::class))),
        @ApiResponse(responseCode = "404", description = "존재하지 않는 판매자",
            content = @Content(schema = @Schema(implementation = ProductInfoResponse.class)))})
    fun getBySellerId(@PathVariable sellerId: String, model: Model): List<ProductInfoResponse> {
        val products = productRepository.findBySellerId(sellerId)
        return products.map{ productInfo ->
            ProductInfoResponse.productInfo2Response(productInfo)
        }
    } */

    @ResponseBody
    @GetMapping("/market/product/{productId}")
    @Operation(description = "해당 상품 ID의 상품 조회")
    fun getByProductId(@PathVariable productId: Int, model: Model): ResponseEntity<ProductInfo> {
        val product = productRepository.findByProductId(productId)
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
        return ResponseEntity.ok(product)
    }

    @GetMapping("/market/product/{productId}/delete")
    @Operation(description = "해당 상품 ID의 상품 삭제")
    fun deleteProduct(@PathVariable productId: Int): String {
        val existingProduct = productRepository.findByProductId(productId)
        if (existingProduct != null) {
            productRepository.delete(existingProduct)
            return "redirect:/deleted"
        }
        throw ResponseStatusException(HttpStatus.NOT_FOUND, "상품을 찾을 수 없습니다.")
    }
}