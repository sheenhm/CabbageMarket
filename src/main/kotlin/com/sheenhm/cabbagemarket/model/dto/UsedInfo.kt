package com.sheenhm.cabbagemarket.model.dto

import com.sheenhm.cabbagemarket.repository.ProductInfo
import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "UsedInfo", title = "중고상품 정보(DTO)")
data class UsedInfo (
    val title: String,
    val sellerId: String,
    val imageUrl: String,
    val price: Int,
) {
    fun toProductInfo(): ProductInfo {
        return ProductInfo(
            productId = 0,
            title = this.title,
            sellerId = this.sellerId,
            imageUrl = this.imageUrl,
            price = this.price,
            issold = 0
        )
    }
}