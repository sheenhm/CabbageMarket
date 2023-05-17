package com.sheenhm.cabbagemarket.repository

import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Entity
@Table(name = "my_info")
data class MyInfo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @Column(nullable = false)
    val userId: String,

    @Column(name = "geo_x")
    val geoX: Double,

    @Column(name = "geo_y")
    val geoY: Double,

    val name: String,
    val tel: String
) {
    constructor() : this(0, "", 0.0, 0.0, "", "")
}

@Repository
interface MyInfoRepository: JpaRepository<MyInfo, String> {
    fun findByUserId(userId: String): MyInfo?
}