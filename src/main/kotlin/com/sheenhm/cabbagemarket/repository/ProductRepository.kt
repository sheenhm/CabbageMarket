package com.sheenhm.cabbagemarket.repository

import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Entity
@Table(name = "used_items")
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
) {
    constructor() : this(0, "", "", null, 0)
}

@Repository
interface ProductRepository: JpaRepository<ProductInfo, String> {
    override fun findAll(): List<ProductInfo>
    fun findByProductId(id: Int): ProductInfo?
    fun findBySellerId(sellerId: String): ProductInfo?
}