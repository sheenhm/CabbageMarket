package com.sheenhm.cabbagemarket.repository

import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Entity
@Table(name = "my_info")
data class MyInfo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    val id: Int,

    @Column(nullable = false)
    val userId: String,

    @Column(name = "geo_x")
    var geoX: Double,

    @Column(name = "geo_y")
    var geoY: Double,

    var name: String,
    var tel: String
) {
    constructor() : this(0, "", 0.0, 0.0, "", "")
}

@Repository
interface MyInfoRepository: JpaRepository<MyInfo, String> {
    fun findByUserId(userId: String): MyInfo?
}