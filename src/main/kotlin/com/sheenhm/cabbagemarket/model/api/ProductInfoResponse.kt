package com.sheenhm.cabbagemarket.model.api

import com.sheenhm.cabbagemarket.repository.ProductInfo
import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "ProductInfo", title = "중고상품 정보(API)")
data class ProductInfoResponse(
    var productId: Int,

    @Schema(title = "중고상품 제목")
    var title: String,

    @Schema(title = "중고상품 판매자")
    var sellerId: String,

    @Schema(title = "중고상품 사진")
    var imageUrl: String?,

    @Schema(title = "중고상품 가격")
    var price: Int,

    @Schema(title = "중고상품 판매여부 - 판매 중이면 0, 판매 완료되었으면 1")
    var issold: Int
) {
    companion object {
        fun productInfo2Response(productInfo: ProductInfo): ProductInfoResponse {
            return ProductInfoResponse(productInfo.productId, productInfo.title, productInfo.sellerId,
                productInfo.imageUrl, productInfo.price, productInfo.issold)
        }
    }
}  // 1. 순수논리적인 모델  2. DTO 역할  3.API용