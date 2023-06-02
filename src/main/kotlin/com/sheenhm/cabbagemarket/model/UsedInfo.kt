package com.sheenhm.cabbagemarket.model

import com.sheenhm.cabbagemarket.repository.ProductInfo

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