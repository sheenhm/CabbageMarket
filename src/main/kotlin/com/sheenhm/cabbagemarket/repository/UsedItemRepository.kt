package com.sheenhm.cabbagemarket.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.persistence.*

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

    @ManyToOne
    @JoinColumn(name = "seller_id")
    val seller: Int
) {
    constructor() : this(0, "", null, 0, 0)
}

@Repository
interface UsedItemRepository: JpaRepository<UsedItem, Int> {
    fun findBySellerId(sellerId: Int): List<UsedItem>
}
