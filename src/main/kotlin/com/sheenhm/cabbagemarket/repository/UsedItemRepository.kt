package com.sheenhm.cabbagemarket.repository

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository

data class UsedItem(
    val id: Int,
    val title: String,
    val imageUrl: String?,
    val price: Int,
    val sellerId: Int
)

@Repository
class UsedItemRepository(private val jdbcTemplate: JdbcTemplate) {

    private val usedItemRowMapper = RowMapper<UsedItem> { rs, _ ->
        UsedItem(
            rs.getInt("id"),
            rs.getString("title"),
            rs.getString("image_url"),
            rs.getInt("price"),
            rs.getInt("seller_id")
        )
    }

    fun getItem(): List<UsedItem> {
        val sql = "SELECT * FROM used_items"
        return jdbcTemplate.query(sql, usedItemRowMapper)
    }

    fun sell(title: String, imageUrl: String?, price: Int, sellerId: Int): Boolean {
        val sql = "INSERT INTO used_items (title, image_url, price, seller_id) VALUES (?, ?, ?, ?)"
        val count = jdbcTemplate.update(sql, title, imageUrl, price, sellerId)
        return count == 1
    }
}