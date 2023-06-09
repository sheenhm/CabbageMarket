package com.sheenhm.cabbagemarket.repository

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Entity
@Table(name = "used_items")
@Schema(name = "ProductInfo", title = "중고상품 정보")
data class ProductInfo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var productId: Int,

    @Column(nullable = false)
    var title: String,

    @Column(name = "seller_id")
    var sellerId: String,

    @Column(name = "image_url")
    var imageUrl: String?,

    @Column(nullable = false)
    var price: Int,

    @Column(name = "is_sold")
    var issold: Int
) {
    constructor() : this(0, "", "", null, 0, 0)
}

@Repository
interface ProductRepository: JpaRepository<ProductInfo, String> {
    override fun findAll(): List<ProductInfo>
    fun findByProductId(id: Int): ProductInfo?
    fun findByTitle(title: String): ProductInfo?
    fun findBySellerId(sellerId: String): List<ProductInfo>
}