package com.sheenhm.cabbagemarket.repository

import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Entity
@Table(name = "used_items")
data class UsedItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @Column(nullable = false)
    val title: String,

    @Column(name = "image_url")
    val imageUrl: String? = null,

    @Column(nullable = false)
    val price: Int,

    @Column(name = "seller_id")
    val sellerId: Int
) {
    constructor() : this(0, "", null, 0, 0)
}

@Repository
interface UsedItemRepository: JpaRepository<UsedItem, Int> {
    fun findBySellerId(sellerId: Long): List<UsedItem>
}